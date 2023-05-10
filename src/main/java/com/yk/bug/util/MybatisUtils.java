package com.yk.bug.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
//    SqlSessionFactory表示的是sql会话工厂
    private static SqlSessionFactory sqlSessionFactory;

//    静态类先加载
    static {
        try {
            //使用Mybatis第一步获取sqlSessionFactory对象
//            配置文件叫啥先写好
            String resource = "mybatis-config.xml";
//            然后通过输入流拿到该文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
//            SqlSessionFactoryBuilder().build(输入流)表示要创建一个sqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    sqlSessionFactory可以创建一个SqlSession的实例
    // SqlSession就是完全包含数据库执行sql语句的命令所需的所有方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
