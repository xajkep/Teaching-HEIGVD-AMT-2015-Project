package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.services.TestDataManager;
import java.io.IOException;
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
    private TestDataManager tdm;
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
        System.out.println("Generate Data");
        tdm.generateTestData();
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

}
