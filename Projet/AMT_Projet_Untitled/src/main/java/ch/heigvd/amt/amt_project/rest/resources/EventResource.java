package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.rest.dto.EventDTO;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author mberthouzoz
 */
@Stateless
@Path("events")
public class EventResource {

    public Response sendEvent(EventDTO event) {
        
        Response.ResponseBuilder builder = Response.ok();
        
        return builder.build();
    }
}
