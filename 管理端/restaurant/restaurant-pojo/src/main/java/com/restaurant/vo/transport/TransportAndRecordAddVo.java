package com.restaurant.vo.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportAndRecordAddVo {

    private Long userId;

    private Long productId;

    private Long companyId;

    private String remarks;

    private String name;

    // 记录运输记录的地点。
    private String lng;
    private String lat;
}
