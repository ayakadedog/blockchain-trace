package com.project.bookstore.mapper;


import com.project.bookstore.pojo.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface GoodMapper extends BaseMapper<Good> {

    @Select("select * from good where status = 0")
    List<Good> getStatus();

    @Update("UPDATE good\n" +
            "SET status = 1\n" +
            "WHERE status = 0;\n")
    void updateStatus();
}
