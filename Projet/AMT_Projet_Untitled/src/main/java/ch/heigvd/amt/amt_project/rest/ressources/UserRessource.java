package ch.heigvd.amt.amt_project.rest.ressources;

import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.rest.DTO.EndUserReputationDTO;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author thsch
 */

@Stateless
@Path("users")
public class UserRessource {
    
    @EJB
    EndUsersDAOLocal endUserDAO;
    
    public UserRessource(){};
    
    @GET
    @Path("{endUserID}/reputation")
    public EndUserReputationDTO getEndUserReputation(@PathParam("endUserID") long endUserID){
        
    }
    
}
