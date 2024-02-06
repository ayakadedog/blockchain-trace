package com.project.bookstore.controller;

import com.project.bookstore.pojo.R;
import com.project.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ResponseBody
    @PostMapping("/insertOrders")
    public R insertOrders(String orderID,Integer uid,Integer aid,Double total,String paymentWay,String deliverWay){
        return orderService.insertOrders( orderID,uid,aid , total, paymentWay, deliverWay);
    }

    @ResponseBody
    @GetMapping("/getOrderById")
    public R getOrderById(String orderID){
        return orderService.getOrderById(orderID);
    }

    @ResponseBody
    @GetMapping("/getOrders")
    public R getOrders(Integer uid){
        return orderService.getOrders(uid);
    }

    @ResponseBody
    @GetMapping("/updateState")
    public R updateState(String orderID,String orderState){
        return orderService.updateState(orderID, orderState);
    }

    @ResponseBody
    @PostMapping("/insertSuborders")
    public R insertSuborders(Integer bookID, Integer quantity, String orderID,Integer uid){
        return orderService.insertSuborders(bookID,quantity,orderID,uid);
    }
    @ResponseBody
    @GetMapping("/getAllOrders")
    public R getAllOrders(){
        return orderService.getAllOrders();
    }
    @ResponseBody
    @GetMapping("/getSuborders")
    public R getSuborders(String orderID){return orderService.getSuborders(orderID);}
}
