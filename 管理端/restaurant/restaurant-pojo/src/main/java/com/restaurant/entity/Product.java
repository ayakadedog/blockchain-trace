package com.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 *  农产品实体
 */
public class Product {
    // 产品ID
    private Long id;

    // 农户ID
    private Long userId;

    // 农户名称
    private String userName;

    // 农户联系方式
    private String userTelephone;

    // 农产品名称
    private String name;

    // 备注描述
    private String description;

    // 种植地点
    private String productionLocation;

    // 种植方式
    private String productionMethod;

    // 产品类型
    private String type;

    // 种植时间
    private LocalDateTime productDate;

    // 状态，0代表已经被送了，1代表还在农户手里
    private Integer status;
}
