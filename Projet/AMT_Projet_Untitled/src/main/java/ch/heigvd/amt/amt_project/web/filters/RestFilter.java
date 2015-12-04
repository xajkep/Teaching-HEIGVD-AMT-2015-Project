package ch.heigvd.amt.amt_project.web.filters;

import ch.heigvd.amt.amt_project.services.dao.ApiKeysDAOLocal;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;



/**
 *
 * @author xajkep
 */
@Provider
public class RestFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @EJB
    private ApiKeysDAOLocal apiKeysDAO;
    
    public RestFilter() {}
    
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String apikey = requestContext.getHeaderString("Authorization");
        
        // Bad request
        if (apikey == null || apikey.equals("")) {
            throw new BadRequestException("Apikey is missing");
        } else if(!apiKeysDAO.exists(apikey)) {
            throw new NotAuthorizedException("This apikey doesn't exist");
        }
        
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        
    }
    
}
