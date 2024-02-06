package com.restaurant.dto;

import com.restaurant.entity.TransportRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportDto {

    private Long id;

    // 关联产品的标识符Transport
    private Long productId;

    // 农户名字
    private String name;

    //农户id
    private Long farmerId;

    // 记录运输记录的地点Transport
    private String location;

    // 记录运输记录的时间
    private LocalDateTime recordTime;

    private String remarks;

    private Integer status;

    private List<TransportRecord> list;

}
