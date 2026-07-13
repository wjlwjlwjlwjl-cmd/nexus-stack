package com.nexus.nexusadminservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nexus.nexusadminaqi.constants.MapConstants;
import com.nexus.nexusadminservice.dao.MapDao;
import com.nexus.nexusadminservice.domain.dto.SysRegionDTO;
import com.nexus.nexusadminservice.domain.entity.SysRegion;
import com.nexus.nexusadminservice.service.IMapService;

import com.nexus.nexuscommoncore.utils.BeanCopyUtil;
import com.nexus.nexuscommonredis.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@SuppressWarnings({"null"})
public class MapServiceImpl implements IMapService{
    @Autowired
    MapDao dao;

    @Autowired
    RedisService redisService;

    @Override
    public List<SysRegionDTO> get_list() {
        List<SysRegionDTO> regions;
        regions = redisService.getCacheObject(MapConstants.CACHE_MAP_CITY_KEY, new TypeReference<List<SysRegionDTO>>(){});
        if(regions != null){
            return regions;
        }
    
        regions = new ArrayList<>();
        List<SysRegion> rets = dao.selectAllRegion();
        for(SysRegion region: rets){
            if(region.getLevel().equals(MapConstants.CITY_LEVEL)){  //从查询结果中过滤出所有城市
                SysRegionDTO item = new SysRegionDTO();
                BeanCopyUtil.copyProperties(region, item);
                regions.add(item);
            }
        }

        redisService.setCacheObject(MapConstants.CACHE_MAP_CITY_KEY, regions);
        log.warn("向Redis中存储城市列表");
        return regions;
    }
}