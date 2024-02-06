package com.restaurant.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名判断账户中是否有这个人
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);
}
