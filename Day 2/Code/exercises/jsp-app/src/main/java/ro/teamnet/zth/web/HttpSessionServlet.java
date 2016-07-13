package ro.teamnet.zth.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by nicoleta on 7/13/2016.
 */
public class HttpSessionServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession httpSession = req.getSession();

        if (username.equals("admin") && password.equals("admin")) {
            res.getWriter().write("Welcome back, Username: " + username + " " + httpSession.getId()+ "!");
        } else {
            req.setAttribute("username", username);
            req.setAttribute("session", httpSession);
            res.sendRedirect("loginFail.jsp");
        }

    }

}
