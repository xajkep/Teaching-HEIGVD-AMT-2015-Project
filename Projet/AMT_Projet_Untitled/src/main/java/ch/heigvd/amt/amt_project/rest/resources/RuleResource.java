package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import ch.heigvd.amt.amt_project.rest.dto.RuleDTO;
import ch.heigvd.amt.amt_project.services.dao.ActionTypesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EventTypeDAOLocal;
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
 * @author xajkep
 */
@Stateless
@Path("rules")
public class RuleResource {

    @EJB
    RulesDAOLocal rulesDAOLocal;
    
    @EJB
    EventTypeDAOLocal eventTypeDAO;
    
    @EJB
    ActionTypesDAOLocal actionTypesDAOLocal;
    
    @EJB
    ApplicationsDAOLocal applicationDAO;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response add(
            RuleDTO dto,
            @HeaderParam("Authorization") String apikey) {
        Rule rule = new Rule();
        
        /* Set event and action type */
        try {
            eventTypeDAO.findByName(
                    dto.getCondition().getType().getName(),
                    applicationDAO.findByApikey(apikey).getId());
            
            actionTypesDAOLocal.findByName(
                    dto.getAction().getType().getName());
        } catch (BusinessDomainEntityNotFoundException ex) {
            throw new ServiceUnavailableException("No content available");
        }
        
        /* Recover event/action properties from the DTO */
        HashMap<String, String> conditionPropertiesMap = dto.getCondition().getProperties();
        HashMap<String, String> actionPropertiesMap = dto.getAction().getProperties();
        
        List<RuleProperties> conditionPropertiesList = new ArrayList<>();
        for (Map.Entry<String, String> entry : conditionPropertiesMap.entrySet()) {
            RuleProperties p = new RuleProperties();
            p.setName(entry.getKey());
            p.setValue(entry.getValue());
            conditionPropertiesList.add(p);
        }
        
        List<RuleProperties> actionPropertiesList = new ArrayList<>();
        for (Map.Entry<String, String> entry : actionPropertiesMap.entrySet()) {
            RuleProperties p = new RuleProperties();
            p.setName(entry.getKey());
            p.setValue(entry.getValue());
            actionPropertiesList.add(p);
        }
        
        rule.setEventProperties(conditionPropertiesList);
        rule.setActionProperties(actionPropertiesList);
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