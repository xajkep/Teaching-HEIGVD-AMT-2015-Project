package ch.heigvd.amt.amt_project.rest.config;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This class is used to send proper HTTP status codes when certain exceptions
 * happen. We don't want Glassfish to respond with a 200 status code and an
 * HTML page saying that an error has happened.
 * 
 * @author mberthouzoz
 */
@Provider
public class JsonExceptionMapper implements ExceptionMapper<NotFoundException> {

  private class Body {
    private String message;

    public Body(String message) {
      this.message = message;
    }
    
    public String getMessage() {
      return message;
    }
  
  }
  
  @Override
  public Response toResponse(NotFoundException e) {
    return Response
      .status(404)
      .entity(new Body(e.getMessage()))
      .type("application/json")
      .build();
  }
  
}
