package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import ch.heigvd.amt.amt_project.models.ActionPoints;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.BadgeAward;
import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.PointAwards;
import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import ch.heigvd.amt.amt_project.rest.dto.EventDTO;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgeAwardsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAO;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.EventTypesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulePropertiesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulesDAOLocal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Response;

/**
 *
 * @author mberthouzoz
 */
@Stateless
@Path("events")
public class EventResource {

    @EJB
    EventTypesDAOLocal eventTypesDAO;

    @EJB
    EndUsersDAOLocal endUserDAO;

    @EJB
    ApplicationsDAOLocal applicationsDAO;

    @EJB
    RulesDAOLocal rulesDAO;

    @EJB
    RulePropertiesDAOLocal rulePropertiesDAO;

    @EJB
    PointAwardsDAOLocal pointAwardsDAO;

    @EJB
    BadgeAwardsDAOLocal badgeAwardsDAO;

    @EJB
    BadgesDAOLocal badgesDAO;

    @POST
    @Consumes("application/json")
    public Response sendEvent(EventDTO event,
            @HeaderParam("Authorization") String apiKey) {
        Response.ResponseBuilder builder = null;
        try {
            String endUserName = event.getEnduser();
            HashMap<String, String> properties = event.getProperties();
            String timestamp = event.getTimestamp();

            Calendar cal = javax.xml.bind.DatatypeConverter.parseDateTime(timestamp);
            Date date = new Date(cal.getTimeInMillis());

            String type = event.getType();
            Application app = null;
            try {
                app = applicationsDAO.findByApikey(apiKey);
            } catch (BusinessDomainEntityNotFoundException ex) {
                Logger.getLogger(EventResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            Long appId = app.getId();
            EndUser endUser = null;
            try {
                endUser = endUserDAO.findByName(endUserName, appId);
            } catch (BusinessDomainEntityNotFoundException ex) {
                endUser = endUserDAO.createAndReturnManagedEntity(new EndUser(app, endUserName, date));
            }
            EventType eventType = null;
            String eventTypeName = event.getType();
            try {
                eventType = eventTypesDAO.findByName(eventTypeName, appId);
            } catch (BusinessDomainEntityNotFoundException ex) {
                throw new ServiceUnavailableException("Event Type not exists");
            }
            List<RuleProperties> ruleProperties = new ArrayList<>();
            for (Entry<String, String> entry : event.getProperties().entrySet()) {
                try {
                    ruleProperties.add(rulePropertiesDAO.findByValueAndName(entry.getKey(), entry.getValue()));
                } catch (BusinessDomainEntityNotFoundException ex) {
                    throw new ServiceUnavailableException("Propertie not exists : " + entry.getKey() + " " + entry.getValue());
                }
            }

            List<Rule> rules = rulesDAO.findByPropertiesAndEventType(ruleProperties, eventTypeName);

            for (Rule r : rules) {
                switch (r.getActionType().getClass().getSimpleName()) {
                    case "ActionPoints":
                        ActionPoints ap = (ActionPoints) r.getActionType();
                        pointAwardsDAO.create(new PointAwards(endUser, ap.getPoints(), null));
                        break;
                    case "ActionBagde":
                        ActionBadge ab = (ActionBadge) r.getActionType();
                        badgeAwardsDAO.create(new BadgeAward(ab.getBadge(), endUser, date));
                }
            }
            builder = Response.ok();

            return builder.build();
        } catch (BusinessDomainEntityNotFoundException ex) {
            Logger.getLogger(EventResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder.build();
    }
}
