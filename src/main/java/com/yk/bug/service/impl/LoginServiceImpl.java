package com.yk.bug.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yk.bug.dao.LoginMapper;
import com.yk.bug.pojo.User;
import com.yk.bug.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public void isLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String body = new String(request.getInputStream().readAllBytes());
        JSONObject json = JSONObject.parseObject(body);
//        decode解码
        String name = URLDecoder.decode(json.getString("name"), StandardCharsets.UTF_8);
        String inv = json.getString("inv");
        int num = json.getIntValue("num");
        User user = loginMapper.selectByInv(inv);
        if (user == null) {
            return;
        }
        // 获取session 并存值
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        if (num == 1) {
            response.sendRedirect(request.getContextPath() + "/randompage/randompage.html");
        } else {
//            encode编码
            Cookie cookie = new Cookie("name", URLEncoder.encode(name, StandardCharsets.UTF_8));
            cookie.setMaxAge(60 * 60);
            cookie.setPath(request.getContextPath() + "/homepage/homepage.html");
            response.addCookie(cookie);
            response.sendRedirect(request.getContextPath() + "/homepage/homepage.html");
        }
    }

}
