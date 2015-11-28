package ch.heigvd.amt.amt_project.web.filters;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;



/**
 *
 * @author xajkep
 */


@Provider
public class RestFilter implements ContainerRequestFilter, ContainerResponseFilter {

    public RestFilter() {}
    
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String apikey = requestContext.getHeaderString("Authorization");
        
        // Bad request
        if (apikey == "" || apikey == null) {
            throw new BadRequestException("Apikey is missing");
        }
        
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        
    }
    
}
