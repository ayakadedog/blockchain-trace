package com.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecord {
    private Long id;

    // 农产品id，不能为空
    private Long productId;

    // 农户id，可为空
    private Long userId;

    // 温度
    private String temperature;

    // 光照情况
    private String illumination;

    // 记录图片路径
    private String picture;

    // 水分
    private String water;

    // 生长情况
    private String growthSituation;

    // 记录时间
    private LocalDateTime recordTime;

    // 备注
    private String remark;

}
