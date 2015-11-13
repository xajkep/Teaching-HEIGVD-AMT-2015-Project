package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.services.dao.AccountsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAO;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mberthouzoz
 */
public class HomeServlet extends HttpServlet {
    
    @EJB
    private ApplicationsDAOLocal applicationsDAO;
    @EJB
    private AccountsDAOLocal accountsDAO;
    @EJB
    private EndUsersDAOLocal endUsersDAO;
    
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
        try {
            /* Stats */
            request.setAttribute("numberOfAccount", accountsDAO.count());
            request.setAttribute("numberOfApplication", applicationsDAO.count());
            request.setAttribute("numberOfUserDuringLast30Days", endUsersDAO.getNumberOfUserDuringLast30Days());
            
            request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
        } catch (BusinessDomainEntityNotFoundException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
