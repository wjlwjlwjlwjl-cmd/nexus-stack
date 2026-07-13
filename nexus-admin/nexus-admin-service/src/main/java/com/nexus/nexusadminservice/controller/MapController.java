package com.nexus.nexusadminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexusadminaqi.domain.RegionVO;
import com.nexus.nexusadminservice.domain.dto.SysRegionDTO;
import com.nexus.nexusadminservice.service.impl.MapServiceImpl;
import com.nexus.nexuscommoncore.utils.BeanCopyUtil;
import com.nexus.nexuscommondomain.domain.R;

@RestController
@RequestMapping("/map")
public class MapController {
    @Autowired
    MapServiceImpl mapServiceImpl;

    @RequestMapping("/get_list") 
    public R<List<RegionVO>> get_list(){
        List<SysRegionDTO> regions = mapServiceImpl.get_list();
        List<RegionVO> ret = BeanCopyUtil.copyListProperties(regions, RegionVO::new);
        return R.ok(ret);
    }
}
