package com.yk.bug.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CheckPath extends HttpFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String servletPath = req.getServletPath();
        System.out.println("path " + servletPath);
        if ("/".equals(servletPath)) {
            if (req.getHeader("Sec-Ch-Ua-Mobile").equals("?1")) {
                resp.sendRedirect(req.getContextPath() + "/login/mobileLogin.html");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login/login.html");
            }
        } else {
            filterChain.doFilter(req, resp);
        }
    }

}
