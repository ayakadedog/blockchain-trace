package com.restaurant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockQueryVo {
    private int page;     // 页码，默认值为 1

    private int limit;    // 每页数量，默认值为 20

    private String name;   // 作物名称

    private String type;   // 作物类型

    private Long id;    //作物id
}
