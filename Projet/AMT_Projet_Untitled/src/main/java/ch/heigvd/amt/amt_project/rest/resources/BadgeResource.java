package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.BadgeAward;
import ch.heigvd.amt.amt_project.rest.dto.BadgeDTO;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    
    @EJB
    private ApplicationsDAOLocal applicationDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
                    .path(BadgeResource.class, "getBadge")
                    .build(badge.getId());

            dto.setHref(badgeHref);

            result.add(dto);
        }

        return result;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{badgeid}")
    public BadgeDTO getBadge(@PathParam("badgeid") long badgeid) {
        BadgeDTO dto = new BadgeDTO();
        Badge badge = badgesDAO.findById(badgeid);
        populateDTOfromEntity(badge, dto);
        
        URI href = uriInfo.getRequestUri();
        
        dto.setHref(href);
        
        return dto;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response add(
            BadgeDTO dto, 
            @HeaderParam("Authorization") String apiKey) {
        Badge badge = new Badge();
        badge.setBadgeAwards(new ArrayList<BadgeAward>());
        badge.setDescription(dto.getDescription());
        badge.setPicture(dto.getPicture());
        
        try {
            badge.setApp(applicationDAO.findByApikey(apiKey));
        } catch (BusinessDomainEntityNotFoundException e) {
            throw new ServiceUnavailableException("No content available");
        }
        
        badgesDAO.create(badge);
        
        URI badgeURI = uriInfo
                .getBaseUriBuilder()
                .path(BadgeResource.class)
                .path(BadgeResource.class, "getBadge")
                .build(badge.getId());
        
        System.out.println(badgeURI);
        
        dto.setHref(badgeURI);
        
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{badgeid}")
    public Response edit(
        BadgeDTO dto,
        @PathParam("badgeid") long badgeid, 
        @HeaderParam("Authorization") String apiKey) {
        
        Badge badge = badgesDAO.findById(badgeid);
        
        if (dto.getDescription() != "") {
            badge.setDescription(dto.getDescription());
        } else {
            dto.setDescription(badge.getDescription());
        }
        
        if (dto.getPicture() != "") {
            badge.setPicture(dto.getPicture());
        } else {
            dto.setPicture(badge.getPicture());
        }
        
        URI badgeURI = uriInfo
                .getBaseUriBuilder()
                .path(BadgeResource.class)
                .path(BadgeResource.class, "getBadge")
                .build(badge.getId());
        
        dto.setHref(badgeURI);
        
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    private void populateDTOfromEntity(Badge badge, BadgeDTO dto){
        dto.setDescription(badge.getDescription());
        dto.setPicture(badge.getPicture());
    }
    
    
    
}
