package com.restaurant.vo.transport;

import com.restaurant.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportVo {
    private Location startCoord;
    private Location center;
    private Location endCoord;
    private List<Location> path;
}
