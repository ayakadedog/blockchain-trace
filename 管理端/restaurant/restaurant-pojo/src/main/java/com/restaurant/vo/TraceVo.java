package com.restaurant.vo;

import com.restaurant.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraceVo {
    private Good good;
    private Company company;
    private Task task;
    private List<TaskRecord> taskRecord;
    private Product product;
    private List<ProductRecord> productRecord;
    private List<TransportRecord> transportRecord;
    private List<TransportRecordVo> transportRecordVos;
}
