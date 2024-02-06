package com.project.bookstore.service;

import com.project.bookstore.pojo.Book;
import com.project.bookstore.pojo.R;
import org.apache.ibatis.annotations.Param;


public interface BookService {
    R showBook();
    R selectBookByType(String type);
    R showItem(int bookID);
    R insertBook(int bookNumber,String bookName,String author,String press,String cover,double price,String type,String content);
    R updateBook(Integer bookID, double price,String content);
    R updateBookType(Integer bookID, String type);
    R deleteBook(int bookID);
    R searchBook(String bookName);
    R updateStock(Integer bookID,Integer stock);
}
