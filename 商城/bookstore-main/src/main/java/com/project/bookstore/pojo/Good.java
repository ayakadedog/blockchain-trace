package com.project.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Good {

    private Long id;
    private Long taskId;
    private Long productId;
    private Long userId;
    private String userName;
    private String phone;


    private String name;        //产品名字
    private String location;    //生产位置
    private String store;       //存储方法
    private String time;        //生产耗时
    private String recommended; //使用推荐
    private String weight;      //产品重量
    private String remarks;     //备注
    private Integer status;

    private String picture;
    private String type;
    private BigDecimal price;

    private int temp;

}
