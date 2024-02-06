package com.restaurant.vo.task;

import com.fasterxml.jackson.databind.node.LongNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddVo {
    private  Long userId;
    private Long productId;
    private String  taskName;
    private String remarks;

}
