package com.adminservice.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.adminservice.config.domain.entity.SysDictionaryType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典类型表 Mapper 接口
 */
@Mapper
public interface SysDictionaryTypeMapper extends BaseMapper<SysDictionaryType> {
}
