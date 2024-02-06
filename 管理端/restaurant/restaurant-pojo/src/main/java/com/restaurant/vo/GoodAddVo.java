package com.restaurant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodAddVo implements Serializable {

    /**
     * 品名
     */
    private String name;

    /**
     * 店铺
     */
    private String store;

    /**
     * 时间
     */
    private String time;

    /**
     * 推荐
     */
    private String recommended;

    /**
     * 重量
     */
    private String weight;

    /**
     * 备注
     */
    private String remarks;

    //生产位置
    private String location;
    private Long taskId;

    private String picture;
    private String type;
    private BigDecimal price;


}