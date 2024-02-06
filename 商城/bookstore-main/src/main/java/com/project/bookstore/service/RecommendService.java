package com.project.bookstore.service;

import com.project.bookstore.pojo.R;
import org.apache.ibatis.annotations.Param;

public interface RecommendService {
    R userCF(int uid);
    R itemCF(int bookID);
    R listRecommend();
    R insertRecommend(Integer bookID,String bookName,String author,String cover,Double price);
    R deleteRecommend(Integer bookID);
}
