package com.restaurant.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddVo {

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
    private String productDate;

}
