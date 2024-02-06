package com.project.bookstore.mapper;

import com.project.bookstore.pojo.Address;
import com.project.bookstore.pojo.Consumer;
import com.project.bookstore.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumerMapper extends MyMapper<Consumer> {
    public Consumer selectUser(String account);
    public Consumer login(@Param("account") String account, @Param("identity")int identity);

    void insertUser(@Param("account")String account, @Param("password")String password,@Param("identity")int identity, @Param("uname")String uname,@Param("himg")String himg);

    Consumer getUserByUid(Integer uid);

    void updateUserName(@Param("uid") Integer uid, @Param("uname") String uname);

    void changePassword(@Param("uid") Integer uid, @Param("password") String password);

    int insertAddress(@Param("uid") Integer uid,@Param("name") String name, @Param("address") String address,@Param("phone")String phone);

    List<Address> listAddress(Integer uid);

    int updateAddress(@Param("aid") Integer aid,@Param("name") String name, @Param("address") String address,@Param("phone") String phone);
    int deleteAddress(Integer aid);
    List<Consumer> listUser();
    int deleteUser(Integer uid);
    int updateHimg(Integer uid,String himg);
}
