package ro.teamnet.zth.web;

<<<<<<< HEAD
import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
=======
import javax.servlet.*;
import java.io.IOException;
>>>>>>> 8addadda9b94709aeb35e629b630215cab0ff8be

/**
 * Created by user on 7/14/2016.
 */
public class HeadersLogFilter implements Filter{

<<<<<<< HEAD
=======

>>>>>>> 8addadda9b94709aeb35e629b630215cab0ff8be
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
<<<<<<< HEAD
        Enumeration<String> en = servletRequest.getParameterNames();

        while (en.hasMoreElements()) {
            String s = en.nextElement();
            LogFileWriter.logHeader(s, ((HttpServletRequest)servletRequest).getHeader(s));
        }
=======

>>>>>>> 8addadda9b94709aeb35e629b630215cab0ff8be
    }

    @Override
    public void destroy() {

    }
<<<<<<< HEAD

=======
>>>>>>> 8addadda9b94709aeb35e629b630215cab0ff8be
}
