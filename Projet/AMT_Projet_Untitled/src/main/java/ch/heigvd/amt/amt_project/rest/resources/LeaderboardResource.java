package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.rest.DTO.EndUserReputationDTO;
import ch.heigvd.amt.amt_project.rest.DTO.LeaderboardDTO;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author xajkep
 */

@Stateless
@Path("leaderboard")
public class LeaderboardResource {
    
    @EJB
    EndUsersDAOLocal endUserDAO;
    
    @EJB
    PointAwardsDAOLocal pointAwardsDAO;
    
    @GET
    @Produces("application/json")
    @Path("current")
    public List<LeaderboardDTO> getLeaderboard(){
        List<LeaderboardDTO> results = new ArrayList<>();
        
        try {
            List<EndUser> users = endUserDAO.getBestUsers(1, 10); //hardcoded
            
            for (EndUser user : users) {
                LeaderboardDTO tmp = new LeaderboardDTO();
                tmp.setName(user.getName());
                tmp.setPoints(user.getSumPoint());
            }
            
            
        } catch (BusinessDomainEntityNotFoundException ex) {
            Logger.getLogger(LeaderboardResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
}
