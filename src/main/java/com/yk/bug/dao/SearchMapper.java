package com.yk.bug.dao;

import com.yk.bug.pojo.SearchMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface SearchMapper {
    @Select("select * from shopping_t where commodity like '%${commodity}%'")
    ArrayList<SearchMsg> searchByCommodity(@Param("commodity") String commodity);
}
