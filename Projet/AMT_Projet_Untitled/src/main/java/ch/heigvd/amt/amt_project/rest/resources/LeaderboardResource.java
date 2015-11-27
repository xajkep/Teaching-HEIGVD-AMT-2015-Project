package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.rest.dto.LeaderboardDTO;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServiceUnavailableException;
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
            @DefaultValue("10") @QueryParam("size") int size)
            throws NotAuthorizedException, ServiceUnavailableException {
        
        if(size <= 0) { size = 10; }
        
        List<LeaderboardDTO> results = new ArrayList<>();
        
        if(apiKey == null) {
            throw new NotAuthorizedException("");
        } else {
            try {
                
                List<Object[]> users = endUserDAO.getBestUsers(apiKey, size);

                for (Object[] o : users) {
                    LeaderboardDTO tmp = new LeaderboardDTO();
                    tmp.setName((String) o[0]);
                    tmp.setPoints((long) o[1]);
                    results.add(tmp);
                }

            } catch (Exception ex) {
                throw new ServiceUnavailableException("No content available");
            }
        }
        
        return results;
    }
}
