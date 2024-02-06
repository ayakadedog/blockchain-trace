package com.restaurant.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecordAddVo {

    // 农产品id，不能为空
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

}
