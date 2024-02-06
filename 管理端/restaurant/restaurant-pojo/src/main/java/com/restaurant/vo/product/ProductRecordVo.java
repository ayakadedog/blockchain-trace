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
public class ProductRecordVo {

    //作物id
    private Long productId;

    // 温度
    private String temperature;

    // 光照情况
    private String illumination;

    // 备注
    private String remark;

    // 记录图片路径
    private String picture;

    // 水分
    private String water;

    // 生长情况
    private String growthSituation;

    // 记录时间
    private LocalDateTime recordTime;

}
