package com.project.bookstore.controller;

import com.project.bookstore.pojo.R;
import com.project.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    /**
     * 读取用户购物车信息
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("/listCart")
    public R listCart(Integer uid){
        return cartService.listCart(uid);
    }

    /**
     * 加入购物车，如购物车已有该商品则数量加1
     * @param uid
     * @param bookID
     * @param count
     * @return
     */
    @ResponseBody
    @PostMapping("/addCart")
    public R addCart(Integer uid,Integer bookID,Integer count){
        return cartService.addCart(uid, bookID, count);
    }

    /**
     * 修改购物车商品数量
     * @param uid
     * @param bookID
     * @param count
     * @return
     */
    @ResponseBody
    @PostMapping("/updateCart")
    public R updateCart(Integer uid,Integer bookID,Integer count){
        return cartService.updateCart(uid, bookID, count);
    }

    /**
     * 在购物车删除某商品
     * @param cid
     * @return
     */
    @ResponseBody
    @GetMapping("/deleteCart")
    public R deleteCart(Integer cid){
        return cartService.deleteCart(cid);
    }

    /**
     * 清空该用户购物车
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("/dropCartById")
    public R dropCartById(Integer uid){
        return cartService.dropCartById(uid);
    }
}
