package com.restaurant.vo.transport;

import com.restaurant.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportStatus {
    private Long transportId;
    private Integer status;
    private Location location;
}
