package servlets;

import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mikado on 07.09.2016.
 */
public class SignUpServlet extends HttpServlet {

    private final DBService dbService;

    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
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

        if (dbService.getUserByLogin(login) != null ) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("User exist");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        UsersDataSet usersDataSet = new UsersDataSet(login, pass);
        dbService.addUserFull(usersDataSet);

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Registred");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
