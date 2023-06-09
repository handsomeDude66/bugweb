package com.yk.bug.service.impl;

import com.yk.bug.dao.SearchMapper;
import com.yk.bug.pojo.SearchMsg;
import com.yk.bug.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    @Override
    public ArrayList<SearchMsg> search(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        return searchMapper.searchByCommodity(request.getParameter("txt"));
    }
}
