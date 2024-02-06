package com.project.bookstore.service;

import com.project.bookstore.pojo.R;


public interface CartService {
    R listCart(Integer uid);
    R addCart(Integer uid,Integer bookID,Integer count);
    R updateCart(Integer uid,Integer bookID,Integer count);
    R deleteCart(Integer cid);
    R dropCartById(Integer uid);
}
