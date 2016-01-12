package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EventTypesDAOLocal;
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
    private EventTypesDAOLocal eventDAO;
    
    @EJB
    private RulePropertiesDAOLocal rulePropertiesDAO;
    
    @EJB
    private RulesDAOLocal ruleDAO;
    
    @EJB
    private ApplicationsDAOLocal applicationDAO;
    
    @EJB
    private BadgesDAOLocal badgeDAO;
    
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
                    eventType = eventDAO.findByName(eventName, app);
                } catch (BusinessDomainEntityNotFoundException ex) {
                    Logger.getLogger(RulesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                List<Rule> rules = null;
                try {
                    rules = ruleDAO.findByEventType(eventType);
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
            request.setAttribute("events", eventDAO.findAll());
            request.setAttribute("badges", badgeDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/pages/rules.jsp").forward(request, response);
        }
        
                
        /*List<EventType> events = eventDAO.findAll();
        
        List<Rule> properties = new ArrayList<>();
        for (EventType event : events) {
            try {
                properties.addAll(ruleDAO.findByEventType(event));
            } catch (BusinessDomainEntityNotFoundException ex) {
                Logger.getLogger(RulesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        
        
    }
    
}
