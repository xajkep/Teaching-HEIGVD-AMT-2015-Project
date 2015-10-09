package ch.heigvd.amt.amt_project.web.controllers;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.Role;
import ch.heigvd.amt.amt_project.services.AccountDAOLocal;
import static ch.heigvd.amt.amt_project.web.controllers.AppServlet.LIST_APP;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

    
    
    private String NAME_PATTERN = "[a-z -]{3,32}";
    private String EMAIL_PATTERN = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
    
    private AccountDAOLocal accountsDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = "";
        
        if (action != null) {

            // Edition
            if (action.equalsIgnoreCase("edit")) {
                forward = EDIT_ACCOUNT;
                
                String id = request.getParameter("id");
                if (id != null) {
                    long accountId = Integer.parseInt(id);
                    if (accountId >= 0) {
                        System.out.println("Account in edition"); //debug
                    }
                }
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
            if (id != null) {
                long accountId = Integer.parseInt(id);
                if (accountId >= 0) {
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
                if (email.matches(EMAIL_PATTERN)) {
                    System.out.println("Account in registration"); //debug

                    // Save new in db ...
                    Role datRole = new Role("SS", new ArrayList<Account>());
                    List<Role> lstRoles = new ArrayList<Role>();
                    lstRoles.add(datRole);
                    Account account = new Account(email, firstname, lastname, password, lstRoles);
                    accountsDAO.create(account);

                    // auto sign in ?
                    forward = AppServlet.LIST_APP;
                } else {
                    request.setAttribute("message", "This email is not valid");
                }
            }
            
        // Invalid form
        } else if (action != null) {
            forward = action.equalsIgnoreCase("edit") ? EDIT_ACCOUNT : REGISTER_ACCOUNT;
        }
        
        request.setAttribute("NAME_PATTERN", NAME_PATTERN);
        request.getRequestDispatcher(forward).forward(request, response);
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
            System.out.println("This lastname is not allowed"); //debug
            return false;
        }

        else if (!confirm.equals(password)) {
            System.out.println("Passwords does not match"); //debug
            return false;
        }
        
        return true;
    }
}
