package com.project.bookstore.service.impl;

import com.project.bookstore.mapper.BookMapper;
import com.project.bookstore.mapper.GoodMapper;
import com.project.bookstore.pojo.Book;
import com.project.bookstore.pojo.Good;
import com.project.bookstore.pojo.R;
import com.project.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Resource
    GoodMapper goodMapper;
    @Resource
    GoodServiceImpl goodService;
    @Override
    public R showBook() {
        try {
//            List<Good> list = goodMapper.getStatus();
//            if (!list.isEmpty()){
//                for (int i=0;i<list.size();i++){
//                    int o = bookMapper.insertBook(1, list.get(i).getName(), list.get(i).getWeight(),
//                            list.get(i).getRecommended(), list.get(i).getPicture(), list.get(i).getPrice().doubleValue(),
//                            list.get(i).getType(), list.get(i).getRemarks(),list.get(i).getId());
//                }
//            }
            List<Good> list = goodMapper.getStatus();

            if (!list.isEmpty()) {
                for (Good good : list) {
                    bookMapper.insertBook(1, good.getName(), good.getWeight(),
                            good.getRecommended(), good.getPicture(), good.getPrice().doubleValue(),
                            good.getType(), good.getRemarks(), good.getId());
                    good.setStatus(1);
                }
                goodMapper.updateStatus();
            }

            List<Book> bookList=bookMapper.showBook();
            if (bookList.size()==0){
                return R.FAIL("没有获取到书籍");
            }
            return R.SUCCESS(bookList,"已获取所有书本");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R selectBookByType(String type) {
        try {
            List<Book> books=bookMapper.selectBookByType(type);
            if (books.size()==0){
                return R.FAIL("没有获取到此类书籍");
            }
            return R.SUCCESS(books,"已获取此类书本");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R showItem(int bookID) {
        try {
            Book book=bookMapper.showItem(bookID);
            if (book!=null){
                return R.SUCCESS(book,"成功获取书籍");
            }
            return R.FAIL("获取失败");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R insertBook(int bookNumber,String bookName,String author,String press,String cover,double price,String type,String content) {
        try {
            int i= bookMapper.insertBook(bookNumber,bookName,author,press,cover,price,type,content,null);
            if (i==0){
                return R.FAIL("增加书籍失败！");
            }
            return R.SUCCESS("增加书籍成功");
        }catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错了");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R updateBook(Integer bookID, double price, String content) {
        try {
            int i=bookMapper.updateBook(bookID,price,content);
            if (i==0){
                return R.FAIL("修改书籍失败！");
            }
            return R.SUCCESS("修改书籍成功");
        }catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错了");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public R updateBookType(Integer bookID, String type) {
        try {
            int i=bookMapper.updateBookType(bookID,type);
            if (i==0){
                return R.FAIL("修改书籍类型失败！");
            }
            return R.SUCCESS("修改书籍类型成功");
        }catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错了");
        }
    }

    @Override
    public R deleteBook(int bookID) {
        try {
            int i=bookMapper.deleteBook(bookID);
            if (i==0){
                return R.FAIL("删除书籍失败！");
            }
            return R.SUCCESS("删除书籍成功");
        }catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错了");
        }
    }

    @Override
    public R searchBook(String bookName) {
        try {
            List<Book> bookList=bookMapper.searchBook(bookName);
            if (bookList.size()==0){
                return R.FAIL("没有相关书籍");
            }
            return R.SUCCESS(bookList,"已获取有关书本");
        }catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错了");
        }
    }

    @Override
    public R updateStock(Integer bookID, Integer stock) {
        try {
            int i = bookMapper.updateStock(bookID, stock);
            if (i==0){
                return R.FAIL("修改库存失败！");
            }
            return R.SUCCESS("修改库存成功");
        }catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错了");
        }
    }
}
