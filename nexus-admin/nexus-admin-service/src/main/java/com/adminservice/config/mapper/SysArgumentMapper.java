package com.adminservice.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.adminservice.config.domain.entity.SysArgument;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数表Mapper接口
 */
@Mapper
public interface SysArgumentMapper extends BaseMapper<SysArgument> {
}
