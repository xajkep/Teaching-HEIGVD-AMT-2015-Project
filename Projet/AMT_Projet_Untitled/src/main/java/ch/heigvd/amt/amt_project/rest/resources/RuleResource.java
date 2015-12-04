package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import ch.heigvd.amt.amt_project.models.ActionPoints;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import ch.heigvd.amt.amt_project.rest.dto.RuleDTO;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EventTypesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulesDAOLocal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author xajkep
 */
@Stateless
@Path("rules")
public class RuleResource {

    @EJB
    RulesDAOLocal rulesDAOLocal;
    
    @EJB
    EventTypesDAOLocal eventTypesDAO;
    
    @EJB
    ApplicationsDAOLocal applicationDAO;
    
    @EJB
    BadgesDAOLocal badgesDAO;
    
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
            eventType = eventTypesDAO.findByName(
                    dto.getCondition().getType(), app.getId());
        } catch (BusinessDomainEntityNotFoundException ex) {
            eventType = eventTypesDAO.createAndReturnManagedEntity(new EventType(dto.getCondition().getType(), app));
        }
        
        rule.setEventType(eventType);
        
        
        /* Recover event properties from the DTO */
        HashMap<String, String> conditionPropertiesMap = dto.getCondition().getProperties();
        HashMap<String, String> actionPropertiesMap = dto.getAction().getProperties();
        
        List<RuleProperties> conditionPropertiesList = new ArrayList<>();
        for (Map.Entry<String, String> entry : conditionPropertiesMap.entrySet()) {
            RuleProperties p = new RuleProperties();
            p.setName(entry.getKey());
            p.setValue(entry.getValue());
            conditionPropertiesList.add(p);
        }
        
        rule.setEventProperties(conditionPropertiesList);
        /* Action type */
        switch(dto.getAction().getType()) {
            case "AwardPoints":
                ActionPoints action = new ActionPoints();
                action.setName("ActionPoints");
                action.setPoints(Long.parseLong(
                        dto.getCondition()
                        .getProperties()
                        .get("nbPoints")));
                rule.setActionType(action);
                break;
            case "AwardBagde":
                ActionBadge actionBadge = new ActionBadge();
                actionBadge.setName("ActionBadge");
                Badge badge = badgesDAO.findById(Long.parseLong(
                        dto.getAction()
                        .getProperties()
                        .get("badgeId")));
                actionBadge.setBadge(badge);
                rule.setActionType(actionBadge);
                break;
            default:
                
                break;
        }
        
        rulesDAOLocal.create(rule);
        
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @DELETE
    @Path("/{ruleid}")
    public Response delete(
            @PathParam("ruleid") long ruleid) {
        
        Rule rule = rulesDAOLocal.findById(ruleid);
        rulesDAOLocal.delete(rule);
        
        return Response.status(Response.Status.OK).build();
    }
}