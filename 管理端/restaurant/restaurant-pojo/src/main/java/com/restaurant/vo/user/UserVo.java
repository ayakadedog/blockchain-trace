package com.restaurant.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Long id;

    //姓名
    private String name;

    //账号
    private String username;

    //手机号
    private String phone;

    //描述
    private String introduction;
    // 权限
    private String roles;

    //用户状态 0禁 1起
    private Integer status;

    private Long companyId;
    //用户等级 区分厂家和用户的
    private Integer level;

    private Integer work;

    private LocalDateTime createTime;
}
