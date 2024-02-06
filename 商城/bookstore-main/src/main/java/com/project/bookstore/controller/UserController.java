package com.project.bookstore.controller;

import com.project.bookstore.pojo.R;
import com.project.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 管理员、用户登录
     * @param account
     * @param password
     * @param identity
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public R login(String account, String password, Integer identity){
        return userService.login(account,password,identity);
    }

    /**
     * 用户注册
     * @param account
     * @param password
     * @param identity
     * @param uname
     * @param himg
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public R register(String account,String password,Integer identity,String uname,String himg){
        return userService.addUser(account,password,identity,uname,himg);
    }

    /**
     * 获取用户信息
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("/getUser")
    public R getUser(Integer uid){
        return userService.getUser(uid);
    }

    /**
     * 修改密码
     * @param uid
     * @param password
     * @return
     */
    @ResponseBody
    @GetMapping("/changePassword")
    public R changePassword(Integer uid,String password){
        return userService.changePassword(uid,password);
    }

    /**
     * 修改用户名
     * @param uid
     * @param uname
     * @return
     */
    @ResponseBody
    @GetMapping("/changeUserName")
    public R changeUserName(Integer uid,String uname){
        return userService.changeUserName(uid,uname);
    }

    /**
     * 查询用户所有地址
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("/listAddress")
    public R listAddress(Integer uid){
        return userService.listAddress(uid);
    }

    /**
     * 增加地址
     * @param uid
     * @param name
     * @param address
     * @param phone
     * @return
     */
    @ResponseBody
    @PostMapping("/addAddress")
    public R addAddress(Integer uid,String name,String address,String phone){
        return userService.insertAddress(uid,name,address,phone);
    }

    /**
     * 修改地址
     * @param aid
     * @param name
     * @param address
     * @param phone
     * @return
     */
    @ResponseBody
    @PostMapping("/updateAddress")
    public R updateAddress(Integer aid,String name,String address,String phone){
        return userService.updateAddress(aid,name,address,phone);
    }

    /**
     * 删除地址
     * @param aid
     * @return
     */
    @ResponseBody
    @GetMapping("/deleteAddress")
    public R deleteAddress(Integer aid){
        return userService.deleteAddress(aid);
    }

    @ResponseBody
    @GetMapping("/listUser")
    public  R listUser(){
        return userService.listUser();
    }

    @ResponseBody
    @GetMapping("/deleteUser")
    public R deleteUser(Integer uid){
        return userService.deleteUser(uid);
    }

    @ResponseBody
    @PostMapping("/updateHimg")
    public R updateHimg(Integer uid,String himg){
        return userService.updateHimg(uid, himg);
    }



}
