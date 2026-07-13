package com.nexus.nexusadminservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.benmanes.caffeine.cache.Cache;
import com.nexus.nexusadminaqi.constants.MapConstants;
import com.nexus.nexusadminservice.dao.MapDao;
import com.nexus.nexusadminservice.domain.dto.SysRegionDTO;
import com.nexus.nexusadminservice.domain.entity.SysRegion;
import com.nexus.nexusadminservice.service.IMapService;
import com.nexus.nexuscommoncache.util.CacheUtil;
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
    CacheUtil cacheUtil;

    @Autowired
    RedisService redisService;

    @Autowired
    Cache<String, Object> cache;

    @Override
    public List<SysRegionDTO> get_list() {
        List<SysRegionDTO> regions;
        regions = cacheUtil.getCache(MapConstants.CACHE_MAP_CITY_KEY, new TypeReference<List<SysRegionDTO>>(){}, cache, redisService);
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

        log.warn("向两级缓存中存储");
        cacheUtil.setL2Cache(MapConstants.CACHE_MAP_CITY_KEY, regions, cache, redisService, 1000, TimeUnit.SECONDS);
        return regions;
    }
}