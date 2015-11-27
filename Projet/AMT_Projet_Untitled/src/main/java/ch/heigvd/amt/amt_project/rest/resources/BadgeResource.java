package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.rest.dto.BadgeDTO;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author thsch, xajkep
 */
@Stateless
@Path("badges")
public class BadgeResource {

    @Context
    UriInfo uriInfo;

    @EJB
    private BadgesDAOLocal badgesDAO;

    @GET
    @Produces("application/json")
    @Path("")
    public List<BadgeDTO> getBadges() {
        List<BadgeDTO> result = new ArrayList<>();
        List<Badge> badges = badgesDAO.findAll();

        for (Badge badge : badges) {
            //Long id = b adge.getId();
            BadgeDTO dto = new BadgeDTO();
            populateDTOfromEntity(badge, dto);

            URI badgeHref = uriInfo
                    .getAbsolutePathBuilder()
                    .path(BadgeResource.class, "getBadges")
                    .build(badge.getId());

            dto.setHref(badgeHref);

            result.add(dto);
        }

        return result;
    }
    
    @GET
    @Produces("application/json")
    @Path("/{badgeid}")
    public BadgeDTO getBadge(@PathParam("badgeid") long badgeid) {
        BadgeDTO dto = new BadgeDTO();
        Badge badge = badgesDAO.findById(badgeid);
        populateDTOfromEntity(badge, dto);
        return dto;
    }

    private void populateDTOfromEntity(Badge badge, BadgeDTO dto){
        dto.setDescription(badge.getDescription());
        dto.setPicture(badge.getPicture());
        
    }
    
    
    
}
