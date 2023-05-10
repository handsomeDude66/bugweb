package com.yk.bug.dao;

import com.yk.bug.pojo.User;

public interface LoginMapper {
    User selectByInv(String inv);
}
