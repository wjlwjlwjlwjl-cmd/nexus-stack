package com.nexus.nexusadminservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexusadminaqi.constants.MapConstants;
import com.nexus.nexusadminservice.dao.MapDao;
import com.nexus.nexusadminservice.domain.dto.SysRegionDTO;
import com.nexus.nexusadminservice.domain.entity.SysRegion;
import com.nexus.nexusadminservice.service.IMapService;

import com.nexus.nexuscommoncore.utils.BeanCopyUtil;

@Service
public class MapServiceImpl implements IMapService{
    @Autowired
    MapDao dao;

    @Override
    public List<SysRegionDTO> get_list() {
        List<SysRegion> rets = dao.selectAllRegion();
        List<SysRegionDTO> regions = new ArrayList<>();
        for(SysRegion region: rets){
            if(region.getLevel().equals(MapConstants.CITY_LEVEL)){  //从查询结果中过滤出所有城市
                SysRegionDTO item = new SysRegionDTO();
                BeanCopyUtil.copyProperties(region, item);
                regions.add(item);
            }
        }
        return regions;
    }
}
