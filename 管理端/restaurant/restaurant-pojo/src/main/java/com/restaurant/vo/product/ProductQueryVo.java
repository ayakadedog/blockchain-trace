package com.restaurant.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueryVo {
    private int page;     // 页码，默认值为 1

    private int limit;    // 每页数量，默认值为 20

    private String name;   // 产品名称

    private String type;   // 产品类型

}
