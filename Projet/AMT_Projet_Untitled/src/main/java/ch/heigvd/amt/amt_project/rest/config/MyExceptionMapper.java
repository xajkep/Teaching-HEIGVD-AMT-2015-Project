package ch.heigvd.amt.amt_project.rest.config;

import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Olivier Liechti
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<BusinessDomainEntityNotFoundException> {

  @Override
  public Response toResponse(BusinessDomainEntityNotFoundException exception) {
    return Response.status(404).
      entity(exception.getMessage()).
      type("text/plain").
      build();
  }

}
