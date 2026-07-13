package com.nexus.nexusadminservice.domain.dto;

import lombok.Data;

@Data
public class LocationDTO {
    private Double lat;         //纬度
    private Double lng;         //经度

    public String formatInfo(){ //格式化经纬度
        return lat + "," + lng;
    }
}
