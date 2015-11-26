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
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

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
    
    @Context
    private HttpServletResponse response;
    
    @GET
    @Produces("application/json")
    @Path("current")
    public List<LeaderboardDTO> getLeaderboard(
            @HeaderParam("Authorization") String apiKey,
            @DefaultValue("10") @QueryParam("size") int size){
        
        if(size <= 0) { size = 10; }
        
        List<LeaderboardDTO> results = new ArrayList<>();
        
        if(apiKey == null) {
            response.setStatus(401); // 401 Unauthorized
        } else {
            try {
                System.out.println("[!] apiKey: "+apiKey);
                System.out.println("[!] size: "+size);
                List<EndUser> users = endUserDAO.getBestUsers(apiKey, size);

                for (EndUser user : users) {
                    LeaderboardDTO tmp = new LeaderboardDTO();
                    tmp.setName(user.getName());
                    tmp.setPoints(user.getSumPoint());
                    results.add(tmp);
                }

            } catch (Exception ex) {
                //Logger.getLogger(LeaderboardResource.class.getName()).log(Level.SEVERE, null, ex);

                response.setStatus(204); // 204 No Content
            }
        }
        
        return results;
    }
}
