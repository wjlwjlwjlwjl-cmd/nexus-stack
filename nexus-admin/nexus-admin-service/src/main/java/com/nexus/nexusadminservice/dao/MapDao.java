package com.nexus.nexusadminservice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nexus.nexusadminservice.domain.entity.SysRegion;

@Mapper
public interface MapDao extends BaseMapper<SysRegion> {
    List<SysRegion> selectAllRegion();
}
