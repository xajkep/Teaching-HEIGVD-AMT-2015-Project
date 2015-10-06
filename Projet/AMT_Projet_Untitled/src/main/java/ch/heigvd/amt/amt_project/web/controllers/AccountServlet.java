package ch.heigvd.amt.amt_project.web.controllers;

import static ch.heigvd.amt.amt_project.web.controllers.AppServlet.LIST_APP;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xajkep
 */
public class AccountServlet extends HttpServlet {

    protected static String EDIT_ACCOUNT = "/WEB-INF/pages/edit_account.jsp";
    
    private String NAME_PATTERN = "[A-Z]{1}[a-z -]{2,31}";
    
    //private AccountDAOLocal accountsDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String forward = EDIT_ACCOUNT;
        
        String id = request.getParameter("id");
        
        if (id != null) {
            int accountId = Integer.parseInt(id);

            if (accountId >= 0) {
                System.out.println("Account in edition");

                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String password = request.getParameter("password");
                String confirm = request.getParameter("password_confirm");


                if (firstname == null || lastname == null ||
                    password == null || confirm == null) {

                    System.out.println("All input are required");
                }

                if (!firstname.matches(NAME_PATTERN)) {
                    System.out.println("This firstname is not allowed");
                }

                if (!lastname.matches(NAME_PATTERN)) {
                    System.out.println("This lastname is not allowed");
                }

                if (confirm != password) {
                    System.out.println("Passwords does not match");
                }

                request.setAttribute("NAME_PATTERN", NAME_PATTERN);

                //Account account = accountsDAO.findById(accountId);
                //accountsDAO.update(account, firstname, lastname, password, confirm);
            }
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }
}
