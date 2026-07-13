package com.nexus.nexusadminservice.service;

import java.util.List;
import java.util.Map;

import com.nexus.nexusadminservice.domain.dto.SysRegionDTO;

public interface IMapService {
    public List<SysRegionDTO> get_list();   // 获取城市列表
    public Map<String, List<SysRegionDTO>> get_py_list();
}