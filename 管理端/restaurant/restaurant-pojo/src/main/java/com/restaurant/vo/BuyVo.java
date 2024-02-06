package com.restaurant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyVo {
    private Long id;
    private Long customerId;
    private Long ProductId;
    private String name;
    private Double number;
    private BigDecimal price;
}
