package com.restaurant.vo;

import com.restaurant.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportRecordVo {

    private Long id;

    // 关联产品的标识符。
    private Long productId;

    private Long TransportId;

    // 运输操作员的标识符。
    private Long userId;

    // 记录运输记录的时间
    private LocalDateTime recordTime;

    // 记录运输记录的地点。
    private String lng;
    private String lat;

    private String locationInfo;
    private String name;
    private String phone;

}
