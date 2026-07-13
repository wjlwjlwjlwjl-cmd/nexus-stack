package com.nexus.nexusadminservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void preLoadCityCache(){
        loadCity();
        loadPyCity();
        log.warn("已完成缓存预热");
    }

    private void loadCity(){
        List<SysRegionDTO> regions = new ArrayList<>();
        List<SysRegion> rets = dao.selectAllRegion();
        for(SysRegion region: rets){
            if(region.getLevel().equals(MapConstants.CITY_LEVEL)){  //从查询结果中过滤出所有城市
                SysRegionDTO item = new SysRegionDTO();
                BeanCopyUtil.copyProperties(region, item);
                regions.add(item);
            }
        }
        cacheUtil.setL2Cache(MapConstants.CACHE_MAP_CITY_KEY, regions, cache, redisService, 1000, TimeUnit.SECONDS);
    }

    private void loadPyCity(){
        //从数据库加载所有城市，并构建拼音分类列表，放进缓存
        Map<String, List<SysRegionDTO>> py_list = new HashMap<>();
        List<SysRegion> rets = dao.selectAllRegion();
        for(SysRegion region: rets){
            SysRegionDTO sysRegionDTO = new SysRegionDTO();
            BeanCopyUtil.copyProperties(region, sysRegionDTO);

            String key = region.getPinyin().charAt(0) + "";
            if(py_list.containsKey(key)){
                py_list.get(key).add(sysRegionDTO);
            }
            else{
                List<SysRegionDTO> list = new ArrayList<>();
                list.add(sysRegionDTO);
                py_list.put(key, list);
            }
        }
        cacheUtil.setL2Cache(MapConstants.CACHE_MAP_CITY_PINYIN_KEY, py_list, cache, redisService, 1000, TimeUnit.SECONDS);
    }

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

        cacheUtil.setL2Cache(MapConstants.CACHE_MAP_CITY_KEY, regions, cache, redisService, 1000, TimeUnit.SECONDS);
        return regions;
    }

    @Override
    public Map<String, List<SysRegionDTO>> get_py_list() {
        Map<String, List<SysRegionDTO>> py_list = cacheUtil
            .getCache(MapConstants.CACHE_MAP_CITY_PINYIN_KEY, new TypeReference<Map<String, List<SysRegionDTO>>>() {}, cache, redisService);

        return py_list;
    }
}