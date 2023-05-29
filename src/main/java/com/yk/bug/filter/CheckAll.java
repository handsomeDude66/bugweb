package com.yk.bug.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter
public class CheckAll extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        System.out.println(" all " + servletPath);
        if (servletPath.equals("/login/login.html")) {
            if (request.getHeader("Sec-Ch-Ua-Mobile").equals("?1")) {
                response.sendRedirect(request.getContextPath() + "/login/mobileLogin.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/login/login.html");
            }
        }
        chain.doFilter(request, response);
    }
}
