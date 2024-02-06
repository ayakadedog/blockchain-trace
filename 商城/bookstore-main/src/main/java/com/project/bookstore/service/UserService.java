package com.project.bookstore.service;

import com.project.bookstore.pojo.R;

public interface UserService {
    R login(String account,String password,int identity);

    R addUser(String account, String password,int identity, String uname,String himg);

    R getUser(Integer uid);

    R changeUserName(Integer uid, String uname);

    R changePassword(Integer uid, String password);

    R listAddress(Integer uid);

    R insertAddress(Integer uid,String name, String address,String phone);

    R updateAddress(Integer aid,String name, String address, String phone);
    R deleteAddress(Integer aid);
    R listUser();
    R deleteUser(Integer uid);
    R updateHimg(Integer uid,String himg);
}
