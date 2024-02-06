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
public class Task {

    private Long id;
    private Long productId;
    private Long companyId;
    private Long userId;
    private String taskName;
    private LocalDateTime recordTime;
    private String remarks;
    private Integer status;

}
