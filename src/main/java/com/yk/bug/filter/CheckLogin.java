package com.yk.bug.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter
public class CheckLogin extends HttpFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String servletPath = req.getServletPath();
        System.out.println("login " + servletPath);
//        用户：要是点击homepage/homepage.html
//        session != null && session.getAttribute("name") != null 拦截
        if (session != null && session.getAttribute("name") != null) {
            filterChain.doFilter(req, resp);
        } else {
            System.out.println(req.getHeader("user-agent"));
            if (req.getHeader("user-agent").contains("Android") || req.getHeader("user-agent").contains("iPhone")) {
                resp.sendRedirect(req.getContextPath() + "/login/login.html");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login/mobileLogin.html");
            }

        }
    }

}
