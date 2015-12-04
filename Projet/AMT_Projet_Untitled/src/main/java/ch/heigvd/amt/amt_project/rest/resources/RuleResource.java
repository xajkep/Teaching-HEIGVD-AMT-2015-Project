package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.rest.dto.RuleDTO;
import ch.heigvd.amt.amt_project.services.dao.ActionTypesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EventTypeDAOLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
        
        try {
            eventTypeDAO.findByName(
                    dto.getCondition().getType().getName(),
                    applicationDAO.findByApikey(apikey).getId());
            
            actionTypesDAOLocal.findByName(
                    dto.getAction().getType().getName());
        } catch (BusinessDomainEntityNotFoundException ex) {
            throw new ServiceUnavailableException("No content available");
        }
        
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
}