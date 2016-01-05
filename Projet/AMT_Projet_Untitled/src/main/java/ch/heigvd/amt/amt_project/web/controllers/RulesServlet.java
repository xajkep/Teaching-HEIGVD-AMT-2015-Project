package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.models.RuleProperties;
import ch.heigvd.amt.amt_project.services.dao.EventTypesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RulePropertiesDAOLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thsch
 */


public class RulesServlet extends HttpServlet{
    
    
    @EJB
    private EventTypesDAOLocal eventDAO;
    
    @EJB
    private RulePropertiesDAOLocal rulePropertiesDAO;
    
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
        request.setAttribute("events", eventDAO.findAll());
        request.setAttribute("ruleProperties", rulePropertiesDAO.findAll());
        
        
        request.getRequestDispatcher("/WEB-INF/pages/rules.jsp").forward(request, response);
    }
    
    

    
}
