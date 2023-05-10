package com.yk.bug.web;

import com.yk.bug.service.BugServiceImpl;
import com.yk.bug.service.HomeServiceImpl;
import com.yk.bug.service.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/loginIn", "/home", "/bug"})
public class LoginServletImpl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/loginIn".equals(servletPath)) {
            LoginServiceImpl.isLogin(req, resp);
        } else if ("/home".equals(servletPath)) {
            HomeServiceImpl.goHome(req, resp);
        } else if ("/bug".equals(servletPath)) {
            try {
                BugServiceImpl.realBug(req, resp);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
