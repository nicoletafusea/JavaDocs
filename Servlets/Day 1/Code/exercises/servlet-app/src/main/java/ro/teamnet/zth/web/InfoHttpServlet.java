package ro.teamnet.zth.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicoleta on 7/12/2016.
 */
public class InfoHttpServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Enumeration<String> e = req.getHeaderNames();
        HashMap<String, ArrayList<String>> hash = new HashMap<>();

        String htmlTable = "<div><table border=\"1\"><tr>";

        while (e.hasMoreElements()) {
            String s = e.nextElement();
            htmlTable = htmlTable + "<th>" + s + "</th>";
            ArrayList<String> arr = new ArrayList<>();
            Enumeration<String> en = req.getHeaders(s);
            while (en.hasMoreElements()) {
                arr.add(en.nextElement());
            }
            hash.put(s, arr);
        }
        htmlTable = htmlTable + "</tr><tr>";

        for (String s : hash.keySet()) {
            htmlTable = htmlTable + "<td>" + hash.get(s).toString() + "</td>";
        }

        htmlTable.concat("</tr></table></div>");

        htmlTable = htmlTable + "<div> Method: " + req.getMethod() + ", QueryString: " +
            req.getQueryString();
        htmlTable = htmlTable + "</div>";

        htmlTable = htmlTable + "<div><table border=\"1\"><tr><th>Cookies</th></tr>";

        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            htmlTable = htmlTable + "<tr><td></td></tr>";
        } else {
            for (int i = 0 ; i < cookies.length ; i++) {
                htmlTable = htmlTable + "<tr><td>" + cookies[i].getName() + "</td></tr>";
            }
        }

        htmlTable = htmlTable + "</table></div>";

        Enumeration<String> ep = req.getParameterNames();
        ArrayList<String> parval = new ArrayList<>();
        htmlTable = htmlTable + "<div><table border=\"1\"><tr>";

        while (ep.hasMoreElements()) {
            String s = ep.nextElement();
            parval.add(req.getParameter(s));
            htmlTable = htmlTable + "<th>" + s + "</th>";
        }

        htmlTable = htmlTable + "</tr><tr>";

        for (String s : parval) {
            htmlTable = htmlTable + "<td>" + s + "</td>";
        }

        htmlTable.concat("</tr></table></div>");
        res.getWriter().write(htmlTable);
    }

}
