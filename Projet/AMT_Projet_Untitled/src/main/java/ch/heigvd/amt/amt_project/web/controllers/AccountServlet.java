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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String forward = EDIT_ACCOUNT;
        
        // ...

        request.getRequestDispatcher(forward).forward(request, response);
    }
}
