package com.restaurant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.dto.user.UserLoginDTO;
import com.restaurant.entity.User;
import com.restaurant.vo.user.UserQueryVo;
import com.restaurant.vo.user.UserVo;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 微信用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    List<User> getTransporters(Long companyId);

    List<UserVo> getUserList(UserQueryVo userQueryVo, User user);
}
