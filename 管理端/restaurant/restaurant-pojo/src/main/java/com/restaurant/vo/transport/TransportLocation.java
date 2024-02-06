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
public class TransportLocation {

    private Long transportId;

    private String lng;
    private String lat;
}
