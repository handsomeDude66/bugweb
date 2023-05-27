package com.yk.bug.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {
    void isLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException;
}
