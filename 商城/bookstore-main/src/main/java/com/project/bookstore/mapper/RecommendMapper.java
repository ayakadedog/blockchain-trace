package com.project.bookstore.mapper;

import com.project.bookstore.pojo.Recommend;
import com.project.bookstore.pojo.RelateDTO;
import com.project.bookstore.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendMapper extends MyMapper<RelateDTO> {
    List<RelateDTO> listRelate();
    List<Recommend> listRecommend();
    int insertRecommend(@Param("bookID")Integer bookID,@Param("bookName")String bookName, @Param("author")String author, @Param("cover")String cover, @Param("price")Double price);
    int deleteRecommend(Integer bookID);
    int insertRelate(@Param("uid")Integer uid,@Param("bookID")Integer bookID);
    RelateDTO selectRelate(@Param("uid")Integer uid,@Param("bookID")Integer bookID);
    int updateRelate(@Param("uid")Integer uid,@Param("bookID")Integer bookID);
}
