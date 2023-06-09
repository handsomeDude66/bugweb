package com.yk.bug.service;

import com.yk.bug.pojo.SearchMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public interface SearchService {
    ArrayList<SearchMsg> search(HttpServletRequest request, HttpServletResponse response)
            throws IOException;
}
