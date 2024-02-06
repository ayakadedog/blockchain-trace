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
public class Material {

    private Long id;

    private Long userId;

    private String userName;

    private Long companyId;

    private String name;

    private String description;

    private String type;

    private LocalDateTime arrivalDate;

    private Integer status;

    private String userTelephone;
}
