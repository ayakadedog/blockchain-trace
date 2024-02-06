package com.restaurant.vo.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskQueryVo {
    private int page;     // 页码，默认值为 1

    private int limit;    // 每页数量，默认值为 20

    private String name;   // 工作名称

    private Integer status;   // 工作完成类型

    private Long id;    //工作id
}
