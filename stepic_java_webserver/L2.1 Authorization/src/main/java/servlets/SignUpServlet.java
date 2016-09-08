package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mikado on 07.09.2016.
 */
public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login == null || pass == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (accountService.isUserExist(login)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("User exist");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        UserProfile newUser = new UserProfile(login, pass);
        accountService.addNewUser(newUser);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Registred");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
