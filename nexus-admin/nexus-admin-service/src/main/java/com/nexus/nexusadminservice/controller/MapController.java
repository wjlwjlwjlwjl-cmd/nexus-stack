package com.nexus.nexusadminservice.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/get_py_list")
    public R<Map<String, List<RegionVO>>> get_py_list(){
        Map<String, List<RegionVO>> py_list = new LinkedHashMap<>();
        Map<String, List<SysRegionDTO>> list = mapServiceImpl.get_py_list();

        for(Map.Entry<String, List<SysRegionDTO>> item: list.entrySet()){
            List<RegionVO> regionVOs = BeanCopyUtil.copyListProperties(item.getValue(), RegionVO::new);
            py_list.put(item.getKey(), regionVOs);
        }
        return R.ok(py_list);
    }

    @RequestMapping("/get_child_list")
    public R<List<RegionVO>> get_child_list(int parentId){
        List<SysRegionDTO> list = mapServiceImpl.get_child_list(parentId);
        List<RegionVO> ret = BeanCopyUtil.copyListProperties(list, RegionVO::new);
        return R.ok(ret);
    }
}
