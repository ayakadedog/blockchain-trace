package com.project.bookstore.service;

import com.project.bookstore.pojo.Orders;
import com.project.bookstore.pojo.R;
import org.apache.ibatis.annotations.Param;


public interface OrderService {
    R insertOrders(String orderID,int uid,int aid,double total,String paymentWay,String deliverWay);

    R getOrderById(String orderID);

    R getOrders(Integer uid);

    R updateState(String orderID,String orderState);

    R getAllOrders();
    R insertSuborders(int bookID,int quantity,String orderID,int uid);
    R getSuborders(String orderID);
}
