package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.constant.MessageConstant;
import com.restaurant.constant.StatusConstant;
import com.restaurant.dto.user.UserLoginDTO;
import com.restaurant.entity.User;
import com.restaurant.exception.AccountLockedException;
import com.restaurant.exception.OrderBusinessException;
import com.restaurant.exception.PasswordErrorException;
import com.restaurant.mapper.UserMapper;
import com.restaurant.service.UserService;
import com.restaurant.vo.user.UserQueryVo;
import com.restaurant.vo.user.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.constant.UserConstant.*;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private UserMapper userMapper;


    /**
     * 员工登录
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new OrderBusinessException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (user.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象

        return user;
    }

    @Override
    public List<User> getTransporters(Long companyId) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", companyId);
        queryWrapper.eq("work", OK_WORK);
        queryWrapper.eq("level", TRANSPORT_WORKER);
        return list(queryWrapper);
    }

    @Override
    public List<UserVo> getUserList(UserQueryVo userQueryVo, User user) {
        String name = userQueryVo.getName();
        Integer status = userQueryVo.getStatus();
        Integer page = userQueryVo.getPage();
        Integer limit = userQueryVo.getLimit();

        Page<User> pageInfo = new Page(page, limit);


        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getCompanyId,user.getCompanyId());
//        queryWrapper.eq(User::getLevel,FARMER);
        queryWrapper.notIn(User::getLevel, 1, 3, 5);

        queryWrapper.like(StringUtils.isNotEmpty(name), User::getName, name);
        queryWrapper.eq(status != null, User::getStatus, status);
        queryWrapper.orderByDesc(User::getCreateTime);

        IPage<User> userPage = this.page(pageInfo, queryWrapper);
        List<User> userList = userPage.getRecords();

        return userList.stream()
                .map(user1 -> {
                    UserVo userVo = new UserVo();
                    BeanUtils.copyProperties(user1, userVo);
                    return userVo;
                })
                .collect(Collectors.toList());
    }

}
