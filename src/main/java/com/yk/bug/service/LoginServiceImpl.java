package com.yk.bug.service;

import com.alibaba.fastjson.JSONObject;
import com.yk.bug.dao.LoginDao;
import com.yk.bug.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class LoginServiceImpl {
    public static void isLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String body = new String(request.getInputStream().readAllBytes());
        JSONObject json = JSONObject.parseObject(body);
        Map<String, User> users = new HashMap<>();
        boolean index = false;
        String name = json.getString("name");
        String inv = json.getString("inv");
        LoginDao loginDao = new LoginDao();
        User user = loginDao.selectByInv(inv);
        if (user != null) {
            users.put("user", user);
            index = true;
        }

        // 获取session 并存值
        if (!index) {
//            直接啥也不传入
            return;
        }
//        否则传入一个json
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        response.sendRedirect(request.getContextPath() + "/home");
    }

}
