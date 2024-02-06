package com.project.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer cid;
    private Integer uid;
    private  Integer bookID;
    private  Integer count;
}
