package com.yk.bug.web;

import com.yk.bug.service.impl.BugService;
import com.yk.bug.service.impl.LoginServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@WebServlet({"/loginIn", "/home", "/bug"})
//@Controller
public class LoginWeb extends HttpServlet {
//    @Autowired
    private LoginServiceImpl loginService ;
    private BugService bugService;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        loginService = ac.getBean(LoginServiceImpl.class);
        bugService = ac.getBean(BugService.class);
        String servletPath = req.getServletPath();
        if ("/loginIn".equals(servletPath)) {
            loginService.isLogin(req, resp);
        } else if ("/bug".equals(servletPath)) {
            try {
                bugService.realBug(req, resp);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
