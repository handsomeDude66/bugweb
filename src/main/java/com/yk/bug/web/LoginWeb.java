package com.yk.bug.web;

import com.yk.bug.pojo.SearchMsg;
import com.yk.bug.service.impl.BugService;
import com.yk.bug.service.impl.LoginServiceImpl;
import com.yk.bug.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

@Controller
//@Transactional
public class LoginWeb {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private BugService bugService;
    @Autowired
    private SearchServiceImpl  searchService;
    @RequestMapping("/loginIn.do")
    public void loginIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.isLogin(request, response);
    }
    @RequestMapping("/bug.do")
    @ResponseBody
    public Map<String, Double> bug(HttpServletRequest req, HttpServletResponse resp) throws IOException, InterruptedException {
        return bugService.realBug(req, resp);
    }
    @RequestMapping("/search.do")
    @ResponseBody
    public ArrayList<SearchMsg> search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("search");
        return searchService.search(req, resp);
    }

}
