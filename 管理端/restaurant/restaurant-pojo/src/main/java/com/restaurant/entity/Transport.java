package com.restaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transport {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 关联产品的标识符。
    private Long productId;

    //运输到达目的地
    private Long companyId;

    //
    private String name;

    // 运输操作员的标识符。
    private Long userId;

    //农户id
    private Long farmerId;

    // 记录运输记录的时间
    private LocalDateTime recordTime;

    private String remarks;

    private Integer status;

    // 记录运输记录的地点。
    private String lng;
    private String lat;
}
