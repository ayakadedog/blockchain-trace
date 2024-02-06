package com.restaurant.vo.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportQueryVo {
    private int page;     // 页码，默认值为 1

    private int limit;    // 每页数量，默认值为 20

    private Long id;   // 作物id 用于查询

    private Integer status;   // 运输状态

}
