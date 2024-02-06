package com.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Location {
    private String lng;
    private String lat;
    public Location(String lng, String lat) {
        this.lng = lng;
        this.lat = lat;
    }

}
