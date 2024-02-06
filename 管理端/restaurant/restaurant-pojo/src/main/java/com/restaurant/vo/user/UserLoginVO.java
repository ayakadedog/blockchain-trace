package com.restaurant.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {

    private Long id;

    private String userName;

    private String name;

    private String avatar;

    private String token;

    private String phone;

    //描述
    private String introduction;

    //
    private List<String> roles;

}
