package com.project.bookstore.mapper;

import com.project.bookstore.pojo.Book;
import com.project.bookstore.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends MyMapper<Book> {
    List<Book> showBook();
    List<Book> selectBookByType(String type);
    Book showItem(int bookID);
    int insertBook(@Param("bookNumber")Integer bookNumber,@Param("bookName")String bookName,
                   @Param("author")String author,@Param("press")String press,
                   @Param("cover")String cover,@Param("price")Double price,
                   @Param("type")String type,@Param("content")String content,@Param("id")Long id);
    int updateBook(@Param("bookID") Integer bookID, @Param("price") Double price, @Param("content") String content);
    int updateBookType(@Param("bookID") Integer bookID, @Param("type") String type);
    int deleteBook(int bookID);
    List<Book> searchBook(String bookName);
    int updateStock(@Param("bookID") Integer bookID, @Param("stock") Integer stock);

    @Select("SELECT *\n" +
            "FROM book\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 4;\n")
    List<Book> rand();
}
