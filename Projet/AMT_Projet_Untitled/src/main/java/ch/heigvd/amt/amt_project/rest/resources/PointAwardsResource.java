package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.PointAwards;
import ch.heigvd.amt.amt_project.rest.dto.PointAwardDTO;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
 * @author xajkep
 */
@Stateless
@Path("pointawards")
public class PointAwardsResource {
    
    @EJB
    private PointAwardsDAOLocal pointAwardsDAO;
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<PointAwardDTO> getPointAwards(
            @HeaderParam("Authorization") String apiKey) {
        List<PointAwardDTO> pointAwardsDTO = new ArrayList<>();
        
        List<PointAwards> pointAwards;
        try {
            pointAwards = pointAwardsDAO.findByApikey(apiKey);
        } catch (BusinessDomainEntityNotFoundException ex) {
            throw new ServiceUnavailableException("No content available");
        }
        System.out.println(pointAwards.size());
        for (PointAwards p : pointAwards) {
            PointAwardDTO dto = new PointAwardDTO();
            
            
            URI href = uriInfo
                    .getAbsolutePathBuilder()
                    .path(PointAwardsResource.class, "getPointAward")
                    .build(p.getId());
            
            
            
            dto.setHref(href);
            dto.setNumberOfPoints(p.getPoint());
            dto.setReason(p.getReason());
            pointAwardsDTO.add(dto);
        }
        
        return pointAwardsDTO;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pointawardid}")
    public PointAwardDTO getPointAward(
            @HeaderParam("Authorization") String apiKey, 
            @PathParam("pointawardid") long pointawardid) {
        PointAwardDTO dto = new PointAwardDTO();
        
        PointAwards p = pointAwardsDAO.findById(pointawardid);
        
        URI href = uriInfo.getRequestUri();
        
        dto.setHref(href);
        dto.setNumberOfPoints(p.getPoint());
        dto.setReason(p.getReason());
        
        return dto;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response add(
            PointAwardDTO dto,
            @HeaderParam("Authorization") String apiKey) {
    
        PointAwards p = new PointAwards();
        p.setPoint(dto.getNumberOfPoints());
        p.setReason(dto.getReason());
        
        pointAwardsDAO.create(p);
        
        URI pointURI = uriInfo
                .getBaseUriBuilder()
                .path(PointAwardsResource.class)
                .path(PointAwardsResource.class, "getPointAward")
                .build(p.getId());
        
        System.out.println(pointURI);
        
        dto.setHref(pointURI);
        
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pointawardid}")
    public Response edit(
            PointAwardDTO dto,
            @HeaderParam("Authorization") String apiKey, 
            @PathParam("pointawardid") long pointawardid) {
    
        PointAwards p = pointAwardsDAO.findById(pointawardid);
        
        if (dto.getNumberOfPoints() > 0) {
            p.setPoint(dto.getNumberOfPoints());
        } else {
            dto.setNumberOfPoints(p.getPoint());
        }
        
        if (dto.getReason() != "") {
            p.setReason(dto.getReason());
        } else {
            dto.setReason(p.getReason());
        }
        
        URI pointURI = uriInfo
                .getBaseUriBuilder()
                .path(PointAwardsResource.class)
                .path(PointAwardsResource.class, "getPointAward")
                .build(p.getId());
        
        dto.setHref(pointURI);
        
        return Response.status(Response.Status.OK).entity(dto).build();
    }
   
    @DELETE
    @Path("/{pointawardid}")
    public Response delete(
        @PathParam("pointawardid") long pointawardid) {
        
        PointAwards p = pointAwardsDAO.findById(pointawardid);
        pointAwardsDAO.delete(p);
        
        return Response.status(Response.Status.OK).build();
    }
}