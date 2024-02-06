package com.restaurant.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecordQueryVo {

    private Long productId; //农产品id

    private int page;     // 页码，默认值为 1

    private int limit;    // 每页数量，默认值为 20

}
