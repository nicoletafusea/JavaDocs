package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by user on 7/14/2016.
 */
public class HeadersLogFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Enumeration<String> en = servletRequest.getParameterNames();

        while (en.hasMoreElements()) {
            String s = en.nextElement();
            LogFileWriter.logHeader(s, ((HttpServletRequest)servletRequest).getHeader(s));
        }
    }

    @Override
    public void destroy() {

    }

}
