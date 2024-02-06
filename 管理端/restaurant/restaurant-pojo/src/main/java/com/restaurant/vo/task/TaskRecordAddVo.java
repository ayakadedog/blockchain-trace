package com.restaurant.vo.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRecordAddVo {
    private Long TaskId;    //taskId
    private String content;//任务内容
    private String remarks; //备注
}
