package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.rest.dto.EventDTO;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.EventTypeDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulesDAOLocal;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Response;

/**
 *
 * @author mberthouzoz
 */
@Stateless
@Path("events")
public class EventResource {
    
    @EJB
    EventTypeDAOLocal eventTypeDAO;
    
    @EJB
    EndUsersDAOLocal endUserDAO;
    
    @EJB
    ApplicationsDAOLocal applicationsDAO;
    
    @EJB
    RulesDAOLocal rulesDAO;

    @POST
    @Consumes("application/json")
    public Response sendEvent(EventDTO event,
            @HeaderParam("Authorization") String apiKey) {
        String endUserName = event.getEnduser();
        HashMap<String, String> properties = event.getProperties();
        String timestamp = event.getTimestamp();

        Calendar cal = javax.xml.bind.DatatypeConverter.parseDateTime(timestamp);
        Date date = new  Date(cal.getTimeInMillis());
        
        String type = event.getType();
        Application app = null;
        try {
            app = applicationsDAO.findByApikey(apiKey);
        } catch (BusinessDomainEntityNotFoundException ex) {
            Logger.getLogger(EventResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long appId = app.getId();
        EndUser endUser = null;
        try {
            endUser = endUserDAO.findByName(endUserName, appId);
        } catch (BusinessDomainEntityNotFoundException ex) {
            endUser = endUserDAO.createAndReturnManagedEntity(new EndUser(app, endUserName, date));
        }
        EventType eventType = null;
        String eventTypeName = event.getType();
        try {
            eventType = eventTypeDAO.findByName(eventTypeName, appId);
        } catch (BusinessDomainEntityNotFoundException ex) {
            throw new ServiceUnavailableException("No content available");
        }
        
        
        
        /*for (Entry<String, String> entry : event.getProperties().entrySet()) {
            System.out.println("Key: " + entry.getKey() + " value: " + entry.getValue());
        }*/
        Response.ResponseBuilder builder = Response.ok();

        return builder.build();
    }
}
