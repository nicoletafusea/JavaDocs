package ro.teamnet.zth.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/12/2016.
 */
public class ZeroToHeroServlet extends HttpServlet {

    private String handleRequest(HttpServletRequest reg) {
        String response = "Hello <b>"+ reg.getParameter("first") + " "
                + reg.getParameter("last") +"</b>! Enjoy Zero To Hero!!!"  ;
        return response;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        res.getWriter().write(handleRequest(req));
    }
}
