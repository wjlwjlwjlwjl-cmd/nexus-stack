package com.nexus.nexusadminservice.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 地图 poi
 * 
 * PoiListDTO
 */
@Data
public class PoiListDTO extends QQMapBaseResponseDTO{
    private Integer count;          //本次搜索结果
    private List<PoiDTO> data;      //查出来的 Poi 列表
}
