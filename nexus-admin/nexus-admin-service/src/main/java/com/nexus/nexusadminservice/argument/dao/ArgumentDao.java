package com.nexus.nexusadminservice.argument.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nexus.nexusadminservice.argument.domain.entity.SysArgument;

@Mapper
public interface ArgumentDao extends BaseMapper<SysArgument> {}