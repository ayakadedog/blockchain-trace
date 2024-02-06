package com.project.bookstore.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int bookID;
    private int bookNumber; //没啥用
    private  String bookName;   //书名        货物名
    private  String author; //作者            分量
    private  String press;  //出版社          使用推荐 recommended
    private  String cover;  //图片            图片
    private  double price;  //售价            售价
    private  String type;   //类型
    private  String content;   //备注         remarks
    private int stock;  //库存

    private Long id;







}
