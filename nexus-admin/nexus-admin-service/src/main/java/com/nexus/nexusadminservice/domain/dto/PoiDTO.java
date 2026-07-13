package com.nexus.nexusadminservice.domain.dto;

import lombok.Data;

@Data
public class PoiDTO {
    private String id;              //poi 地点的唯一标识
    private String title;           //poi 地点的名称
    private String address;         //地址

    private String type;            //poi 类型
    //0: 普通poi，1：公交车站，2：地铁站，3：公交线路，4：行政区划

    private LocationDTO location;   //poi地点所处坐标 
}
