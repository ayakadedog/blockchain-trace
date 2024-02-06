package com.restaurant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryVo {
    private int page;     // 页码，默认值为 1

    private int limit;    // 每页数量，默认值为 20

    private String sortBy; // 排序字段

    private String name;   // 产品名称

    private String type;   // 产品类型

    private String sort;   // 排序方式，默认值为 "+id"
}
