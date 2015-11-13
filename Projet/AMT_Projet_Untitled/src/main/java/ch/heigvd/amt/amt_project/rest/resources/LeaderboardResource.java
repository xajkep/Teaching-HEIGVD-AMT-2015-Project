package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.rest.DTO.EndUserReputationDTO;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author xajkep
 */

@Stateless
@Path("users")
public class LeaderboardResource {
    
    @EJB
    EndUsersDAOLocal endUserDAO;
    
    @GET
    @Path(" /api/leaderboards/current")
    public LeaderboardDTO getLeaderboard(){
        return null;
    }
}
