package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.Role;
import ch.heigvd.amt.amt_project.services.dao.AccountsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RolesDAOLocal;
import static ch.heigvd.amt.amt_project.web.controllers.AppServlet.LIST_APP;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xajkep
 */
public class AccountServlet extends HttpServlet {

    protected static String EDIT_ACCOUNT     = "/WEB-INF/pages/edit_account.jsp";
    protected static String REGISTER_ACCOUNT = "/WEB-INF/pages/register.jsp";
    protected static String HOME = "/WEB-INF/pages/home.jsp";
    
    
    private String NAME_PATTERN = "[a-zA-Z0-9 -]{3,32}";
    private String EMAIL_PATTERN = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    
    @EJB
    private AccountsDAOLocal accountsDAO;
    
    @EJB
    private RolesDAOLocal rolesDAO;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = "";
        
        if (action != null) {

            // Edition
            if (action.equalsIgnoreCase("edit")) {
                forward = EDIT_ACCOUNT;
                
                Account account = (Account)request.getSession().getAttribute("user");
                request.setAttribute("lastname", account.getLastName());
                request.setAttribute("firstname", account.getFirstName());
            }
            
            // Registration
            else if (action.equalsIgnoreCase("new")) {
                forward = REGISTER_ACCOUNT;
            }
        }

        request.setAttribute("NAME_PATTERN", NAME_PATTERN);
        request.setAttribute("EMAIL_PATTERN", EMAIL_PATTERN);
        request.getRequestDispatcher(forward).forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = "";
        
        if (verifyForm(request)) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            
            // Update
            String id = request.getParameter("id");
            Account user = (Account)request.getSession().getAttribute("user");
            if (id != null) {
                long accountId = Integer.parseInt(id);
                if (accountId >= 0 && accountId == user.getId()) {
                    System.out.println("Account in update"); //debug

                    Account account = accountsDAO.findById(accountId);
                    account.setFirstName(firstname);
                    account.setLastName(lastname);
                    account.setPassword(password);
                    accountsDAO.update(account);
                    
                    forward = AppServlet.LIST_APP;
                }
            }

            // Registration
            else {               
                
                String email = request.getParameter("email");
                forward = AccountServlet.HOME;
                if (email.matches(EMAIL_PATTERN) && !accountsDAO.exists(email)) {
                    
                    System.out.println("Account in registration"); //debug

                    // Save new in db ...
                    Account account = accountsDAO.createAndReturnManagedEntity(new Account(email, firstname, lastname, password));
                    Role userRole = rolesDAO.findById((long)2);
                    accountsDAO.assignRoleToAccount(userRole, account);
                } else {
                    request.setAttribute("message", "This email is not valid");
                }
            }
            
        // Invalid form
        } else if (action != null) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            
            forward = action.equalsIgnoreCase("edit") ? EDIT_ACCOUNT : REGISTER_ACCOUNT;
            request.setAttribute("error", "Invalid form");
            request.setAttribute("email", email);
            request.setAttribute("firstName", firstname);
            request.setAttribute("lastName", lastname);
            
            request.getRequestDispatcher(forward).forward(request, response);
        }
        
        response.sendRedirect("home");
    }
    
    
    private boolean verifyForm(HttpServletRequest request) {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String confirm = request.getParameter("password_confirm");
                
        if (firstname == null || lastname == null ||
            password == null || confirm == null) {
            return false;
        }

        else if (!firstname.matches(NAME_PATTERN)) {
            request.setAttribute("message", "This firstname is not allowed");
            return false;
        }

        else if (!lastname.matches(NAME_PATTERN)) {
            request.setAttribute("message", "This lastname is not allowed");
            return false;
        }

        else if (!confirm.equals(password)) {
            request.setAttribute("message", "Passwords does not match");
            return false;
        }
        
        return true;
    }
}
