package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import ch.heigvd.amt.amt_project.models.ActionPoints;
import ch.heigvd.amt.amt_project.models.ActionType;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import ch.heigvd.amt.amt_project.services.dao.ActionBadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ActionPointsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EventTypesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulePropertiesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulesDAOLocal;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thsch, xajkep
 */
public class RulesServlet extends HttpServlet{
    
    
    @EJB
    private EventTypesDAOLocal eventTypesDAO;
    
    @EJB
    private RulePropertiesDAOLocal rulePropertiesDAO;
    
    @EJB
    private RulesDAOLocal rulesDAO;
    
    @EJB
    private ApplicationsDAOLocal applicationDAO;
    
    @EJB
    private BadgesDAOLocal badgesDAO;
    
    @EJB
    private PointAwardsDAOLocal pointAwardsDAO;
    
    @EJB
    private ActionPointsDAOLocal actionPointsDAO;
    
    @EJB
    private ActionBadgesDAOLocal actionBadgesDAO;
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        long appId = Integer.parseInt(request.getParameter("app"));
        
        String ajax = request.getParameter("ajax");
        
        // Ajax calls (for the form)
        if (ajax != null) {
            
            System.out.println("ajax");
            System.out.println(ajax);
            
            // Return event properties
            if (ajax.equalsIgnoreCase("properties")) {
                String eventName = request.getParameter("eventName");
                
                Application app = applicationDAO.findById(appId);
                
                EventType eventType = null;
                try {
                    eventType = eventTypesDAO.findByName(eventName, app);
                } catch (BusinessDomainEntityNotFoundException ex) {
                    Logger.getLogger(RulesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                List<Rule> rules = null;
                try {
                    rules = rulesDAO.findByEventType(eventType);
                } catch (BusinessDomainEntityNotFoundException ex) {
                    Logger.getLogger(RulesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                List<RuleProperties> properties = new ArrayList<>();
                for (Rule rule : rules) {
                    List<RuleProperties> tmp = rule.getEventProperties();
                    for (RuleProperties prop : tmp) {
                        properties.add(prop);
                    }
                }
                
                String json = new Gson().toJson(properties);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.write(json);
                out.flush();                
            }
        } else {
            // Return form
            request.setAttribute("appId", appId);
            request.setAttribute("events", eventTypesDAO.findAll());
            request.setAttribute("badges", badgesDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/pages/rules.jsp").forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /* GET data */
        long appId = Integer.parseInt(request.getParameter("app"));
        
        /* DB data */
        Application app = applicationDAO.findById(appId);
        
        /* POST data */
        
        // Event name
        String eventName;
        if (request.getParameter("new_event") == "") {
            eventName = request.getParameter("event");
        } else {
            eventName = request.getParameter("new_event");
        }
        EventType eventType = eventTypesDAO.createAndReturnManagedEntity(new EventType(eventName, app));
        
        // Rules properties
        List<RuleProperties> ruleProperties = new ArrayList<>();
        
        // Existant prop. IDs
        String[] existantPropIDsStrings = request.getParameterValues("eventPropertiesId");
        for(int i = 0; i < existantPropIDsStrings.length; i++) {
            System.out.println(existantPropIDsStrings[i]); // debug
            
            ruleProperties.add(
                    rulePropertiesDAO.findById(
                            (long)Integer.parseInt(existantPropIDsStrings[i])
                    )
            );
        }
        
        // New prop.
        String[] names = request.getParameterValues("name");
        String[] values = request.getParameterValues("values");
        
        for(int i = 0; i < names.length; i++) {
            ruleProperties.add(
                    rulePropertiesDAO.createAndReturnManagedEntity(
                            new RuleProperties(names[i], values[i])
                    )
            );
        }
        

        // Action type
        ActionType actionType;
        if (request.getParameter("name") == "point") {
            /* code for point award */
            
            // Get number of awarded points
            long numberOfPoints = Integer.parseInt(
                    request.getParameter("numberOfPoints")
            );
            
            actionType = actionPointsDAO.createAndReturnManagedEntity(
                    new ActionPoints(numberOfPoints, "")
            ); 
        } else {
            /* code for badge award */
            
            long badgeId = Integer.parseInt(request.getParameter("badgeId"));
            
            Badge badge = badgesDAO.findById(badgeId);
            
            actionType = actionBadgesDAO.createAndReturnManagedEntity(
                    new ActionBadge(badge, "")
            );
        }
        
        // Finally, create the rule
        rulesDAO.create(new Rule(eventType, ruleProperties, actionType));
    }
    
}
