package com.project.bookstore.service.impl;

import com.project.bookstore.mapper.BookMapper;
import com.project.bookstore.mapper.CartMapper;
import com.project.bookstore.mapper.RecommendMapper;
import com.project.bookstore.pojo.Book;
import com.project.bookstore.pojo.Cart;
import com.project.bookstore.pojo.R;
import com.project.bookstore.pojo.RelateDTO;
import com.project.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    RecommendMapper recommendMapper;
    @Autowired
    BookMapper bookMapper;

    @Override
    public R listCart(Integer uid) {
        try {
            List<Cart> cart=cartMapper.listCart(uid);
            List cartList=new ArrayList();
            if(cart.size()==0){
                return R.FAIL("购物车中还没有商品");
            }else {
                for (Cart ct:cart) {
                    Map<String,Object> item=new HashMap<String,Object>();
                    int bookID=ct.getBookID();
                    Book book=new Book();
                    book= bookMapper.showItem(bookID);
                    item.put("book",book);
                    item.put("cart",ct);
                    cartList.add(item);
                }

            }
            return R.SUCCESS(cartList,"购物车获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R addCart(Integer uid, Integer bookID, Integer count) {
        try {
            Cart cart=cartMapper.getByCartId(uid, bookID);
            RelateDTO relateDTO = recommendMapper.selectRelate(uid,bookID);
            int i,j;
            if (cart!=null){
                i = cartMapper.updateCart(uid, bookID, cart.getCount() + count);
            }else {
                i = cartMapper.addCart(uid, bookID, count);

            }
            if (i!=0){
                if (relateDTO==null){
                    j = recommendMapper.insertRelate(uid,bookID);
                }
            }else {
                return R.FAIL("加入购物车失败");
            }
            return R.SUCCESS("成功加入购物车");

        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R updateCart(Integer uid, Integer bookID, Integer count) {
        try {
            int i=cartMapper.updateCart(uid, bookID, count);
            if (i==0){
                return R.FAIL("修改失败");
            }
            return R.SUCCESS("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R deleteCart(Integer cid) {
        try {
            int i=cartMapper.deleteCart(cid);
            if (i==0){
                return R.FAIL("删除失败");
            }
            return R.SUCCESS("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R dropCartById(Integer uid) {
        try {
            int i=cartMapper.dropCartById(uid);
            if (i==0){
                return R.FAIL("清空购物车失败");
            }
            return R.SUCCESS("清空购物车成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }
}
