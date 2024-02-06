package com.project.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suborders {
    private int suborderID;
    private int bookID;
    private int quantity;
    private String orderID;
}
