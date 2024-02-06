package com.project.bookstore.controller;

import com.project.bookstore.pojo.R;
import com.project.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @ResponseBody
    @GetMapping("/showBook")
    public R showBook(){
        return bookService.showBook();
    }

    @ResponseBody
    @GetMapping("/selectBookByType")
    public R selectBookByType(String type){
        return bookService.selectBookByType(type);
    }

    @ResponseBody
    @GetMapping("/showItem")
    public R showItem(int bookID){
        return bookService.showItem(bookID);
    }

    @ResponseBody
    @PostMapping("/insertBook")
    public R insertBook(Integer bookNumber,String bookName,String author,String press,String cover,Double price,String type,String content){

        return bookService.insertBook(bookNumber, bookName, author, press, cover, price, type, content);
    }

    @ResponseBody
    @PostMapping("/updateBook")
    public R updateBook(Integer bookID, Double price,String content){

        return bookService.updateBook(bookID, price, content);
    }

    @ResponseBody
    @GetMapping("/updateBookType")
    public R updateBookType(Integer bookID, String type){
        return bookService.updateBookType(bookID, type);
    }

    @ResponseBody
    @GetMapping("/deleteBook")
    public R deleteBook(Integer bookID){
        return bookService.deleteBook(bookID);
    }

    @ResponseBody
    @GetMapping("/searchBook")
    public R searchBook(String bookName){
        return bookService.searchBook(bookName);
    }

    @ResponseBody
    @GetMapping("updateStock")
    public R updateStock(Integer bookID,Integer stock){
        return bookService.updateStock(bookID, stock);
    }
}
