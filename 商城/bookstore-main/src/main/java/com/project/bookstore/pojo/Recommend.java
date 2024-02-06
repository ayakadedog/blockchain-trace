package com.project.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommend {
    private Integer bookID;
    private  String bookName;
    private  String author;
    private  String cover;
    private  Double price;
}
