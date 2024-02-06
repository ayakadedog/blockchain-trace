package com.project.bookstore.mapper;

import com.project.bookstore.pojo.Cart;
import com.project.bookstore.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper extends MyMapper<Cart> {
    List<Cart> listCart(int uid);
    int addCart(@Param("uid")int uid,@Param("bookID") int bookID,@Param("count") int count);
    int updateCart(@Param("uid")int uid,@Param("bookID")int bookID,@Param("count") int count);
    int deleteCart(@Param("cid")int cid);
    Cart getByCartId(@Param("uid")int uid,@Param("bookID") int bookID);
    int dropCartById(Integer uid);
}
