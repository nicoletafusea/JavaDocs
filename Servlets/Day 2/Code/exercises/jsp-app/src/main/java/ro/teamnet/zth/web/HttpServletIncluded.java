package ro.teamnet.zth.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/14/2016.
 */
public class HttpServletIncluded extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        String s = request.getAttribute("attribute").toString();
//        response.setContentType("text/html");
//        response.getWriter().write("Hello from Included" + s);

        request.setAttribute("testAttribute", "altceva");
    }
}
