package com.restaurant.vo.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRecordListVo {

    private Long id;
    private Long taskId;
    private Long productId;
    private Long userId;
    private Long companyId;
    private String remarks;
    private String taskName;
    private String content;
    private LocalDateTime recordTime;


    private String name;
    private String phone;

}
