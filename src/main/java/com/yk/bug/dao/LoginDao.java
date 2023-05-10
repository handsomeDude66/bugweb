package com.yk.bug.dao;

import com.yk.bug.pojo.User;
import com.yk.bug.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

public class LoginDao implements LoginMapper {
    public User selectByInv(String inv) {
//        try(里面表示的是要打开的资源) 执行完后自动帮你关闭资源
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
//            获取完SqlSession之后可以就可以执行sql语句了
            return mapper.selectByInv(inv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
