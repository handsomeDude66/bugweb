package com.yk.bug.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter
public class CheckType extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String servletPath = req.getServletPath();
        System.out.println(servletPath);
        if (servletPath.equals("/") ||servletPath.equals("/login")
                || servletPath.equals("/homepage") || servletPath.equals("/homepage/homepage.html") ||
        servletPath.equals("/login/login.html") || servletPath.equals("/login/mobileLogin.html")) {
//          获取session用来存储机型
            HttpSession session = req.getSession();
            if (req.getHeader("user-agent").contains("Android") || req.getHeader("user-agent").contains("iPhone")) {
                session.setAttribute("type", "Phone");
            } else {
                session.setAttribute("type", "Windows");
            }
        }
        chain.doFilter(req, resp);
    }
}
