package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.PointAwards;
import ch.heigvd.amt.amt_project.rest.dto.PointAwardDTO;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
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
    @Produces("application/json")
    @Path("")
    public List<PointAwardDTO> getPointAwards() {
        List<PointAwardDTO> pointAwardsDTO = new ArrayList<>();
        
        List<PointAwards> pointAwards = pointAwardsDAO.findAll();
        System.out.println(pointAwards.size());
        for (PointAwards p : pointAwards) {
            PointAwardDTO dto = new PointAwardDTO();
            
            URI href = uriInfo
                    .getAbsolutePathBuilder()
                    .path(PointAwardsResource.class, "getPointAwards")
                    .build(p.getId());
            
            dto.setHref(href);
            dto.setNumberOfPoints(p.getPoint());
            dto.setReason(p.getReason());
            pointAwardsDTO.add(dto);
        }
        
        return pointAwardsDTO;
    }
    
    @GET
    @Produces("application/json")
    @Path("/{pointawardid}")
    public PointAwardDTO getPointAward(@PathParam("pointawardid") long pointawardid) {
        PointAwardDTO dto = new PointAwardDTO();
        PointAwards p = pointAwardsDAO.findById(pointawardid);
        
        URI href = uriInfo
                    .getAbsolutePathBuilder()
                    .path(PointAwardsResource.class, "getPointAward")
                    .build(p.getId());
        
        dto.setHref(href);
        dto.setNumberOfPoints(p.getPoint());
        dto.setReason(p.getReason());
        
        return dto;
    }
   
}