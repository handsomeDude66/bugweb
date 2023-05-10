package com.yk.bug.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeServiceImpl {
    public static void goHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = (String) req.getSession(false).getAttribute("name");
        Cookie cookie = new Cookie("name", name);
        cookie.setMaxAge(60 * 60);
        cookie.setPath(req.getContextPath() + "/homepage/homepage.html");
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/homepage/homepage.html");
    }
}
