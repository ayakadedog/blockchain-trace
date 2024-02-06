package com.restaurant.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVo {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 产品名称
    private String name;

    // 产品类型
    private String type;

    // 生产者
    private String producer;

    // 生产日期
    private LocalDateTime productDate;

    // 过期日期
    private LocalDateTime expirationDate;

    // 单位
    private String unit;

    // 数量
    private double number;

    // 价格
    private BigDecimal price;

    // 生产地点
    private String productionLocation;

    // 储存条件
    private String storageConditions;

    // 货物描述
    private String description;

    // 供应商
    private String supplier;

    // 图片
    private String image;

    // 销售状态
    private Integer status;
}
