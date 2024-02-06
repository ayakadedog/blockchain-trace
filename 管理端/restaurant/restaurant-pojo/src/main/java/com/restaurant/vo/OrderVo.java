package com.restaurant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {
    private long id;                    // 订单ID
    private Long productId;             // 产品名称
    private Double quantity;               // 订购数量
    private BigDecimal totalPrice;      // 订单总价
    private LocalDateTime deliveryDate; // 交付日期和时间，可为空
    private LocalDateTime orderDate;    // 下单日期和时间
    private Integer status;             // 订单状态，例如：处理中、已发货、已交付

}
