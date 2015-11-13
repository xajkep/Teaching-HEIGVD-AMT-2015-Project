package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.ApiKey;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.services.dao.AccountsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApiKeysDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xajkep, mberthouzoz
 */
public class AppServlet extends HttpServlet {
    @EJB
    private ApplicationsDAOLocal applicationsDAO;
    @EJB
    private AccountsDAOLocal accountsDAO;
    @EJB
    private ApiKeysDAOLocal apiKeysDAO;
    @EJB
    private EndUsersDAOLocal endUsersDAO;
    
    protected static String LIST_APP = "/WEB-INF/pages/app.jsp";
    protected static String NEW_APP = "/WEB-INF/pages/app_new.jsp";
    private static String EDIT_APP = "/WEB-INF/pages/app_edit.jsp";
    private static String LIST_USER_APP = "/WEB-INF/pages/userlist.jsp";
    
    private String NAME_PATTERN = "[a-z0-9A-Z -]{3,32}";
    private String EMAIL_PATTERN = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String forward = "";
        
        Account account = (Account)request.getSession().getAttribute("user");

        if (action != null) {
            if (action.equalsIgnoreCase("edit")) {
                long appId = Integer.parseInt(request.getParameter("id"));
                forward = EDIT_APP;
                
                Application app = applicationsDAO.findById(appId);
                
                request.setAttribute("id", appId);
                request.setAttribute("name", app.getName());
                request.setAttribute("description", app.getDescription());
                request.setAttribute("apiKey", app.getKey().getApiKey());
                request.setAttribute("endUsers", endUsersDAO.getNumberOfUserByApp(appId));
            } else if (action.equalsIgnoreCase("enable")) {
                long appId = Integer.parseInt(request.getParameter("id"));
                
                Application app = applicationsDAO.findById(appId);
                
                app.setEnable(Boolean.TRUE);
                applicationsDAO.update(app);
                
                forward = LIST_APP;
                
                List<Application> apps = applicationsDAO.findAllByUserId(account.getId());
                
                ArrayList<Long> totals = new ArrayList<Long>(); 
                for (Application a : apps) {
                    totals.add(endUsersDAO.getNumberOfUserByApp(a.getId()));
                }
                
                request.setAttribute("apps", apps);
                request.setAttribute("totals", totals);
            } else if (action.equalsIgnoreCase("disable")) {
                long appId = Integer.parseInt(request.getParameter("id"));
                Application app = applicationsDAO.findById(appId);
                
                app.setEnable(Boolean.FALSE);
                applicationsDAO.update(app);
                
                forward = LIST_APP;
                
                List<Application> apps = applicationsDAO.findAllByUserId(account.getId());
                
                ArrayList<Long> totals = new ArrayList<Long>(); 
                for (Application a : apps) {
                    totals.add(endUsersDAO.getNumberOfUserByApp(a.getId()));
                }
                
                request.setAttribute("apps", apps);
                request.setAttribute("totals", totals);
            } else if (action.equalsIgnoreCase("userlist")){
                long appId = Integer.parseInt(request.getParameter("id"));
                
                int total_page = (int) Math.ceil(endUsersDAO.getNumberOfUserByApp(appId) / 10.);
                long current_page;
                
                if (request.getParameter("page") != null) {
                    current_page = Integer.parseInt(request.getParameter("page"));
                } else {
                    current_page = 1;
                }
                
                long prev_page = (current_page > 1 ? current_page-1 : 1);
                long next_page = (current_page < total_page ? current_page+1 : total_page);
                
                forward = LIST_USER_APP;
                request.setAttribute("allUsers", endUsersDAO.findByApp(appId, account.getId(), 10, (int)current_page-1));
                request.setAttribute("app", applicationsDAO.findById(appId));
                request.setAttribute("current_page", current_page);
                request.setAttribute("prev_page", prev_page);
                request.setAttribute("next_page", next_page);
                request.setAttribute("total_page", total_page);
            } else {
                System.out.println("in NEW");
                
                // Generate the API Key
                ApiKey apiKey = apiKeysDAO.createAndReturnManagedEntity(new ApiKey());
                request.getSession().setAttribute("apiKey", apiKey);
                
                forward = NEW_APP;
                request.setAttribute("apiKey", apiKey.getApiKey());
            }
        } else {
            forward = LIST_APP;
            
            // try catch, for empty result
            try{
                List<Application> apps = applicationsDAO.findAllByUserId(account.getId());
                
                ArrayList<Long> totals = new ArrayList<Long>(); 
                for (Application a : apps) {
                    totals.add(endUsersDAO.getNumberOfUserByApp(a.getId()));
                }
                
                request.setAttribute("apps", apps);
                request.setAttribute("totals", totals);
            } catch(NullPointerException e) {
                System.out.println("No applications to list"); //debug
                request.setAttribute("message", "No applications to list");
            }
        }

        request.setAttribute("NAME_PATTERN", NAME_PATTERN);
        request.getRequestDispatcher(forward).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        Account account = (Account)request.getSession().getAttribute("user");
        
        if (action != null) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String idString = request.getParameter("id");
            
            if (name != null && description != null
                && name.matches(NAME_PATTERN)) {
                if (action.equalsIgnoreCase("edit") && idString != null) {
                    long id = Integer.parseInt(idString);
                    
                    Application app = applicationsDAO.findById(id);
                    app.setName(name);
                    app.setDescription(description);
                    applicationsDAO.update(app);
                }

                else if (action.equalsIgnoreCase("new")) {
                    
                    if (request.getSession().getAttribute("apiKey") != null) {
                        ApiKey apiKey = (ApiKey)request.getSession().getAttribute("apiKey");
                        
                        Application app = new Application(name, description, apiKey, true, account);
                        applicationsDAO.create(app);
                    }
                    
                    request.getSession().setAttribute("apiKey", null);
                }
            } else {
                request.setAttribute("message", "Formulaire invalide");
                request.getRequestDispatcher(LIST_APP).forward(request, response);
            }
        }
        
        response.sendRedirect("app");
    }
}
