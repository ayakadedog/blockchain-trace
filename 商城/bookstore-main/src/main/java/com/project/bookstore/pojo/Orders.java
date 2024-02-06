package com.project.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private String orderID;
    private int uid;
    private int aid;
    private double total;
    private String paymentWay;
    private String deliverWay;
    private Date orderTime;
    private String orderState;

}
