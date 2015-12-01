package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.BadgeAward;
import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.rest.dto.BadgeDTO;
import ch.heigvd.amt.amt_project.rest.dto.EndUserDTO;
import ch.heigvd.amt.amt_project.rest.dto.EndUserReputationDTO;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("users")
public class UserResource {

    @Context
    UriInfo uriInfo;

    @EJB
    EndUsersDAOLocal endUsersDAO;
    
    @EJB
    ApplicationsDAOLocal applicationsDAO;

    public UserResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EndUserDTO> getUsers(
        @HeaderParam("Authorization") String apikey) {
        List<EndUserDTO> results = new ArrayList<>();
        
        List<EndUser> endUsers = endUsersDAO.findByApikey(apikey);
        
        for (EndUser e : endUsers) {
            EndUserDTO dto = new EndUserDTO();
            dto.setName(e.getName());
            dto.setHref(uriInfo
                    .getAbsolutePathBuilder()
                    .path(UserResource.class, "getUser")
                    .build(e.getId()));
            results.add(dto);
        }
        
        return results;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{enduserid}")
    public EndUserDTO getUser(
        @PathParam("enduserid") long enduserid,
        @HeaderParam("Authorization") String apikey) {
    
        EndUserDTO dto = new EndUserDTO();
        
        EndUser e = endUsersDAO.findById(enduserid);
        
        dto.setName(e.getName());        
        dto.setHref(uriInfo.getRequestUri());
       
        return dto;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(
            EndUserDTO dto,
            @HeaderParam("Authorization") String apikey) {
        EndUser e = new EndUser();
        e.setName(dto.getName());
        
        // set the application of the enduser
        try {
            e.setApp(applicationsDAO.findByApikey(apikey));
        } catch (BusinessDomainEntityNotFoundException ex) {
            throw new ServiceUnavailableException("No content available");
        }
        
        endUsersDAO.create(e);
        
        dto.setHref(uriInfo
                    .getAbsolutePathBuilder()
                    .path(UserResource.class, "getUser")
                    .build(e.getId()));
        
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userid}")
    public Response edit(
        EndUserDTO dto,
        @PathParam("userid") long userid) {
        EndUser e = endUsersDAO.findById(userid);
        
        if (dto.getName() != "") {
            e.setName(dto.getName());
        } else {
            dto.setName(e.getName());
        }
        
        return Response.status(Response.Status.OK).entity(dto).build();
    }
    
    @DELETE
    @Path("/{userid}")
    public Response delete(
            @PathParam("userid") long userid) {
        EndUser e = endUsersDAO.findById(userid);
        endUsersDAO.delete(e);
        
        return Response.status(Response.Status.OK).build();
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{endUserID}/reputation")
    public EndUserReputationDTO getEndUserReputation(@PathParam("endUserID") long endUserID) {
        EndUser endUser = endUsersDAO.findById(endUserID);

         try {
            EndUserReputationDTO dto = new EndUserReputationDTO();
            dto.setPoints(endUsersDAO.getPoints(endUser.getId()));
            List<BadgeDTO> badgeList = new ArrayList<>();

            for (BadgeAward badgeAward : endUser.getBadgeAwards()) {
                BadgeDTO badgeDTO = new BadgeDTO();
                Badge badge = badgeAward.getBadge();
                badgeDTO.setDescription(badge.getDescription());
                badgeDTO.setPicture(badge.getPicture());

                URI badgeHref = uriInfo
                        .getAbsolutePathBuilder()
                        .path(BadgeResource.class, "getBadge")
                        .build(badge.getId());
                badgeDTO.setHref(badgeHref);

                badgeList.add(badgeDTO);
            }
            return dto;
        } catch (BusinessDomainEntityNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{endUserID}/badges")
    public List<BadgeDTO> getEndUserBadges(@PathParam("endUserID") long endUserID) {
        EndUser endUser = endUsersDAO.findById(endUserID);
        List<BadgeDTO> result = new ArrayList<>();

        for (BadgeAward badgeAward : endUser.getBadgeAwards()) {
                BadgeDTO badgeDTO = new BadgeDTO();
                Badge badge = badgeAward.getBadge();
                badgeDTO.setDescription(badge.getDescription());
                badgeDTO.setPicture(badge.getPicture());

                URI badgeHref = uriInfo
                        .getAbsolutePathBuilder()
                        .path(BadgeResource.class, "getBadge")
                        .build(badge.getId());
                badgeDTO.setHref(badgeHref);

                result.add(badgeDTO);
            }
        return result;
    }
}
