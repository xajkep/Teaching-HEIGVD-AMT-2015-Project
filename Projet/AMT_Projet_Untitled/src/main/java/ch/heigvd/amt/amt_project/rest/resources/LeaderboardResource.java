package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.rest.dto.BadgeDTO;
import ch.heigvd.amt.amt_project.rest.dto.LeaderboardDTO;
import ch.heigvd.amt.amt_project.services.dao.BadgeAwardsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
import java.net.URI;
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
import javax.ws.rs.core.UriInfo;

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
    
    @EJB
    BadgeAwardsDAOLocal badgeAwardsDAO;
    
    @Context
    private UriInfo uriInfo;
    
    @Context
    private HttpServletResponse response;
    
    @GET
    @Produces("application/json")
    @Path("current")
    public List<LeaderboardDTO> getLeaderboard(
            @HeaderParam("Authorization") String apiKey,
            @DefaultValue("10") @QueryParam("size") int size)
            throws NotAuthorizedException, ServiceUnavailableException {
        
        // we only want positive integer
        if(size <= 0) { size = 10; }
        
        List<LeaderboardDTO> results = new ArrayList<>();
        
        try {
            /* get best users */
            List<Object[]> users = endUserDAO.getBestUsers(apiKey, size);

            for (Object[] o : users) {
                LeaderboardDTO tmp = new LeaderboardDTO();
                tmp.setName((String) o[1]);
                tmp.setPoints((long) o[2]);

                /* get user badges */
                List<BadgeDTO> badges = new ArrayList<>();

                List<Object[]> badgesString = badgeAwardsDAO.getByUser((long) o[0]);
                for (Object[] s : badgesString) {
                    BadgeDTO tmp2 = new BadgeDTO();
                    
                    // Build href link to get one badge
                    URI badgeURI = uriInfo
                        .getAbsolutePathBuilder()
                        .path(BadgeResource.class, "getBadge")
                        .build((long) s[0]);
                    
                    tmp2.setHref(badgeURI);
                    tmp2.setPicture((String) s[1]);
                    tmp2.setDescription((String) s[2]);
                    
                    badges.add(tmp2);
                }

                tmp.setBadges(badges);

                results.add(tmp);
            }

        } catch (Exception e) {
            throw new ServiceUnavailableException("No content available");
        }
        
        return results;
    }
}
