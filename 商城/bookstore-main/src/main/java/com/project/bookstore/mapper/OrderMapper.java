package com.project.bookstore.mapper;

import com.project.bookstore.pojo.Orders;
import com.project.bookstore.pojo.Suborders;
import com.project.bookstore.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends MyMapper<Orders> {
    int insertOrders(@Param("orderID")String orderID,@Param("uid")int uid,@Param("aid")int aid,@Param("total")double total,@Param("paymentWay")String paymentWay,@Param("deliverWay")String deliverWay);

    Orders getOrderById(String orderID);

    List<Orders> getOrders(Integer uid);

    int updateState(@Param("orderID") String orderID, @Param("orderState") String orderState);

    List<Orders> getAllOrders();

    int insertSuborders(@Param("bookID")int bookID,@Param("quantity")int quantity,@Param("orderID")String orderID);

    List<Suborders> getSuborders(String orderID);
}
