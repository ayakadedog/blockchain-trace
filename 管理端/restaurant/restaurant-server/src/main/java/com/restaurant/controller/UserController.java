package com.restaurant.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.constant.JwtClaimsConstant;
import com.restaurant.context.BaseContext;
import com.restaurant.dto.TransportDto;
import com.restaurant.dto.user.UserLoginDTO;
import com.restaurant.entity.Product;
import com.restaurant.entity.Transport;
import com.restaurant.entity.User;
import com.restaurant.exception.BaseException;
import com.restaurant.properties.JwtProperties;
import com.restaurant.result.Result;
import com.restaurant.service.UserService;
import com.restaurant.utils.JwtUtil;
import com.restaurant.vo.transport.TransportQueryVo;
import com.restaurant.vo.transport.TransportVo;
import com.restaurant.vo.user.UserLoginVO;
import com.restaurant.vo.user.UserQueryVo;
import com.restaurant.vo.user.UserVo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.restaurant.constant.UserConstant.*;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    private Long empId = 0L;

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        log.info("员工登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);
        request.getSession().setAttribute("user",user);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
        claims.put("roles",user.getRoles());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .name(user.getName())
                .avatar(user.getAvatar())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }


    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("/info")
    public Result<UserLoginVO> getUserInfo(String token) {
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
             empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前id：", empId);
            BaseContext.setCurrentId(empId);
            //3、通过，放行
        } catch (Exception ex) {

            throw new BaseException("身份验证未通过");
        }

        User user = userService.getById(empId);

        user.setRoles(user.getRoles());

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .roles(Collections.singletonList(user.getRoles()))
                .userName(user.getUsername())
                .name(user.getName())
                .avatar(user.getAvatar())
                .build();

        return Result.success(userLoginVO);

    }


    /**
     * 获取用户信息
     *
     * @param
     * @return
     */
    @GetMapping("/get")
    public Result<UserLoginVO> getUser() {

        User user = userService.getById(empId);

        user.setRoles(user.getRoles());

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .roles(Collections.singletonList(user.getRoles()))
                .userName(user.getUsername())
                .name(user.getName())
                .introduction(user.getIntroduction())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .build();

        return Result.success(userLoginVO);

    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 获取运输员列表
     * @param request
     * @return
     */
    @GetMapping("/transport")
    public Result<List<User>> getTransporters(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Long companyId = userService.getById(user.getId()).getCompanyId();
        List<User> list = userService.getTransporters(companyId);
        return Result.success(list);
    }

    /**
     * 员工页面展示
     * @param userQueryVo
     * @param request
     * @return
     */
    @PostMapping("/page")
    public Result<List<UserVo>> transportPage(@RequestBody UserQueryVo userQueryVo, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }
        if (user.getLevel() == 1 || user.getLevel() == 3) {
            List<UserVo> list = userService.getUserList(userQueryVo,user);
            return Result.success(list);
        }

        return Result.error("账号不对");

    }

    /**
     * 停用和启用员工
     * @param userId
     * @return
     */
    @PostMapping("/status")
    public Result<Void> changeStatus(@RequestParam Long userId){

        User user = userService.getById(userId);
        user.setStatus(user.getStatus() == 1 ? 0 : 1);
        userService.updateById(user);
        return Result.success();
    }

    @PostMapping("/employeeList")
    public Result<List<User>> getUserList(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getCompanyId,user.getCompanyId());
        lambdaQueryWrapper.eq(User::getStatus,NO_WORK);
        lambdaQueryWrapper.eq(User::getLevel,2);
        List<User> list = userService.list(lambdaQueryWrapper);
        return Result.success(list);
    }
}
