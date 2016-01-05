package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import ch.heigvd.amt.amt_project.models.ActionPoints;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import ch.heigvd.amt.amt_project.rest.dto.RuleDTO;
import ch.heigvd.amt.amt_project.services.dao.ActionBadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ActionPointsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EventTypesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulePropertiesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulesDAOLocal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xajkep, mberthouzoz
 */
@Stateless
@Path("rules")
public class RuleResource {

    @EJB
    RulesDAOLocal rulesDAO;

    @EJB
    EventTypesDAOLocal eventTypesDAO;

    @EJB
    ApplicationsDAOLocal applicationDAO;

    @EJB
    BadgesDAOLocal badgesDAO;

    @EJB
    RulePropertiesDAOLocal rulePropertiesDAO;

    @EJB
    ActionBadgesDAOLocal actionBadgesDAO;

    @EJB
    ActionPointsDAOLocal actionPointsDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(
            RuleDTO dto,
            @HeaderParam("Authorization") String apikey) {
        Rule rule = new Rule();

        /* Set event and action type */
        Application app = null;
        EventType eventType = null;
        try {
            app = applicationDAO.findByApikey(apikey);
            eventType = eventTypesDAO.findByName(dto.getCondition().getType(), app);
        } catch (BusinessDomainEntityNotFoundException ex) {
            eventType = eventTypesDAO.createAndReturnManagedEntity(new EventType(dto.getCondition().getType(), app));
        }

        rule.setEventType(eventType);

        /* Recover event properties from the DTO */
        HashMap<String, String> conditionPropertiesMap = dto.getCondition().getProperties();

        List<RuleProperties> conditionPropertiesList = new ArrayList<>();
        for (Map.Entry<String, String> entry : conditionPropertiesMap.entrySet()) {
            RuleProperties p = null;
            try {
                p = rulePropertiesDAO.findByValueAndName(entry.getValue(), entry.getKey());
            } catch (BusinessDomainEntityNotFoundException ex) {
                p = rulePropertiesDAO.createAndReturnManagedEntity(new RuleProperties(entry.getKey(), entry.getValue()));
            }
            conditionPropertiesList.add(p);
        }

        rule.setEventProperties(conditionPropertiesList);
        /* Action type */
        switch (dto.getAction().getType()) {
            case "AwardPoints":
                ActionPoints action;
                Long points = Long.parseLong(
                        dto.getAction()
                        .getProperties()
                        .get("nbPoints"));
                try {
                    action = actionPointsDAO.findByPoints(points);
                } catch (BusinessDomainEntityNotFoundException ex) {
                    action = new ActionPoints();
                    action.setName("ActionPoints");
                    action.setPoints(points);
                }

                rule.setActionType(action);
                break;
            case "AwardBagde":
                Badge badge = badgesDAO.findById(Long.parseLong(
                        dto.getAction()
                        .getProperties()        
                        .get("badgeId")));

                ActionBadge actionBadge;

                try {
                    actionBadge = actionBadgesDAO.findByBadge(badge);
                } catch (BusinessDomainEntityNotFoundException ex) {
                    actionBadge = new ActionBadge();
                    actionBadge.setName("ActionBadge");
                    actionBadge.setBadge(badge);
                }

                rule.setActionType(actionBadge);
                break;
            default:

                break;
        }

        if (rulesDAO.exists(rule.getEventProperties(), rule.getEventType(), rule.getActionType())) {
            throw new ServiceUnavailableException("Rule already exists");
        }

        rulesDAO.create(rule);

        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @DELETE
    @Path("/{ruleid}")
    public Response delete(
            @PathParam("ruleid") long ruleid) {

        Rule rule = rulesDAO.findById(ruleid);
        rulesDAO.delete(rule);

        return Response.status(Response.Status.OK).build();
    }
}
