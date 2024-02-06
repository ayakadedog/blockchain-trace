package com.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    /**
     * 农作物id，全部用农作物id
     */
    private Long id;

    /**
     * 原料厂Id
     */
    private Long companyId;

    /**
     * 原料厂名字
     */
    private String companyName;

    /**
     * 作物名称
     */
    private String name;

    /**
     * 作物类型
     */
    private String type;

    /**
     * 到达时间
     */
    private LocalDateTime arrivalDate;

    /**
     * 加工状态：
     * 0 - 未加工
     * 1 - 在加工
     * 2 - 加工完成
     */
    private int status;

    private Long farmerId;
}
