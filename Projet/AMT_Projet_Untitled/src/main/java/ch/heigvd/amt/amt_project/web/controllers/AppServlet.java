package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.services.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.models.Application;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xajkep, mberthouzoz
 */
public class AppServlet extends HttpServlet {
    
    private ApplicationsDAOLocal applicationsDAO;

    protected static String LIST_APP = "/WEB-INF/pages/app.jsp";
    protected static String NEW_APP = "/WEB-INF/pages/app_new.jsp";
    private static String EDIT_APP = "/WEB-INF/pages/app_edit.jsp";
    
    private String NAME_PATTERN = "[a-z -]{3,32}";
    private String EMAIL_PATTERN = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String forward = "";

        if (action != null) {
            if (action.equalsIgnoreCase("edit")) {
                System.out.println("in EDIT");
                int appId = Integer.parseInt(request.getParameter("id"));
                forward = EDIT_APP;
                //Application app = ;
                //request.setAttribut("app", app);
            } else if (action.equalsIgnoreCase("enable")) {
                System.out.println("in ENABLE");
                long appId = Integer.parseInt(request.getParameter("id"));
                // DO SOMETHING TO ENABLE
                Application app = applicationsDAO.findById(appId);
                applicationsDAO.enable(app);
                forward = LIST_APP;
                request.setAttribute("apps", applicationsDAO.findAll());
            } else if (action.equalsIgnoreCase("disable")) {
                System.out.println("in DISABLE");
                long appId = Integer.parseInt(request.getParameter("id"));
                Application app = applicationsDAO.findById(appId);
                applicationsDAO.disable(app);
                // DO SOMETHING TO DISABLE
                forward = LIST_APP;
                request.setAttribute("apps", applicationsDAO.findAll());
            } else {
                System.out.println("in NEW");
                forward = NEW_APP;
            }
        } else {
            forward = LIST_APP;
            
            // try catch, for empty result
            try{
                request.setAttribute("apps", applicationsDAO.findAll());
            } catch(NullPointerException e) {
                System.out.println("No applications to list"); //debug
                request.setAttribute("message", "No applications to list");
            }
        }

        request.setAttribute("NAME_PATTERN", NAME_PATTERN);
        request.setAttribute("EMAIL_PATTERN", EMAIL_PATTERN);
        request.getRequestDispatcher(forward).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = "";
        
        if (action != null) {
            if (action.equalsIgnoreCase("edit")) {
                
                
                forward = LIST_APP;
            }
            
            else if (action.equalsIgnoreCase("new")) {
                
                forward = LIST_APP;
            }
        }
        
        
        request.getRequestDispatcher(forward).forward(request, response);
    }
}
