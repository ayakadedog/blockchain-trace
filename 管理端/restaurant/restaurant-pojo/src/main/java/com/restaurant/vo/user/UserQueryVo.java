package com.restaurant.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryVo {
    private int page;     // 页码，默认值为 1

    private int limit;    // 每页数量，默认值为 20

    private String name;   // 员工名字 用于查询

    private Integer status;   // 员工状态


}
