package com.project.bookstore.service.impl;

import com.project.bookstore.mapper.BookMapper;
import com.project.bookstore.mapper.OrderMapper;
import com.project.bookstore.mapper.RecommendMapper;
import com.project.bookstore.pojo.Book;
import com.project.bookstore.pojo.Orders;
import com.project.bookstore.pojo.R;
import com.project.bookstore.pojo.Suborders;
import com.project.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RecommendMapper recommendMapper;
    @Autowired
    BookMapper bookMapper;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R insertOrders(String orderID,int uid,int aid,double total,String paymentWay,String deliverWay) {
        try {
            int i=orderMapper.insertOrders(orderID,uid,aid,total,paymentWay,deliverWay);
            if (i==0){
                return R.FAIL("提交订单失败");
            }
            return R.SUCCESS("提交订单成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R getOrderById(String orderID) {
        try {
            Orders orders=orderMapper.getOrderById(orderID);
            if (orders!=null){
                return R.SUCCESS(orders,"订单获取成功");
            }
            return R.FAIL("订单获取失败");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R getOrders(Integer uid) {
        try {
            List<Orders> ordersList=orderMapper.getOrders(uid);
            if (ordersList.size()==0){
                return R.FAIL("订单获取失败");
            }
            return R.SUCCESS(ordersList,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R updateState(String orderID, String orderState) {
        try {
            int i = orderMapper.updateState(orderID, orderState);
            if (i==0){
                return R.FAIL("状态修改失败");
            }
            return R.SUCCESS("状态修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R getAllOrders() {
        try {
            List<Orders> ordersList=orderMapper.getAllOrders();
            if (ordersList.size()==0){
                return R.FAIL("还没有订单");
            }
            return R.SUCCESS(ordersList,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R insertSuborders(int bookID, int quantity, String orderID,int uid) {
        try {
            int i=orderMapper.insertSuborders(bookID, quantity, orderID);
            if (i==0){
                return R.FAIL("提交子订单失败");
            }else {
                int j=recommendMapper.updateRelate(uid,bookID);
                Book book=bookMapper.showItem(bookID);
                int stock=book.getStock();
                if(stock>=quantity){
                    int s = bookMapper.updateStock(bookID,stock-quantity);
                }else {
                    return R.FAIL("库存不足");
                }
            }
            return R.SUCCESS("提交子订单成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R getSuborders(String orderID) {
        try {
            List<Suborders> suborders=orderMapper.getSuborders(orderID);
            List subordersList=new ArrayList();
            if (suborders.size()==0){
                return R.FAIL("子订单获取失败");
            }else {
                for (Suborders s:suborders){
                    Map<String,Object> item=new HashMap<String,Object>();
                    int bookID=s.getBookID();
                    Book book=new Book();
                    book= bookMapper.showItem(bookID);
                    item.put("book",book);
                    item.put("suborders",s);
                    subordersList.add(item);
                }
            }
            return R.SUCCESS(subordersList,"子订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }
}
