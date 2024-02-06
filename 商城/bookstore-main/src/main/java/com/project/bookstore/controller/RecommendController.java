package com.project.bookstore.controller;

import com.project.bookstore.pojo.R;
import com.project.bookstore.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class RecommendController {
    @Autowired
    RecommendService recommendService;

    /**
     * 基于用户的系统推荐算法
     * @param uid
     * @return
     */

    @ResponseBody
    @GetMapping("/userCF")
    public R userCF(Integer uid){
        return recommendService.userCF(uid);
    }

    /**
     * 基于物品的协同推荐算法
     * @param bookID
     * @return
     */
    @ResponseBody
    @GetMapping("/itemCF")
    public R itemCF(Integer bookID){
        return recommendService.itemCF(bookID);
    }

    @ResponseBody
    @GetMapping("/listRecommend")
    public R listRecommend(){
        return recommendService.listRecommend();
    }

    @ResponseBody
    @PostMapping("/insertRecommend")
    public R insertRecommend(Integer bookID, String bookName, String author, String cover, Double price){
        return recommendService.insertRecommend(bookID, bookName, author, cover, price);
    }

    @ResponseBody
    @GetMapping("/deleteRecommend")
    public R deleteRecommend(Integer bookID){
        return  recommendService.deleteRecommend(bookID);
    }
}
