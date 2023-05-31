package com.yk.bug.web;

import com.yk.bug.service.impl.BugService;
import com.yk.bug.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
//@Transactional
public class LoginWeb {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private BugService bugService;
    @RequestMapping("/loginIn.do")
    public void loginIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.isLogin(request, response);
    }
    @RequestMapping("/bug.do")
    @ResponseBody
    public Map<String, Double> bug(HttpServletRequest req, HttpServletResponse resp) throws IOException, InterruptedException {
        return bugService.realBug(req, resp);
    }

}
