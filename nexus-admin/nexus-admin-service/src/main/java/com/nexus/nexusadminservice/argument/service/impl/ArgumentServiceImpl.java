package com.nexus.nexusadminservice.argument.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nexus.nexusadminaqi.argument.domain.dto.ArgumentAddReqDTO;
import com.nexus.nexusadminservice.argument.dao.ArgumentDao;
import com.nexus.nexusadminservice.argument.domain.entity.SysArgument;
import com.nexus.nexusadminservice.argument.service.IArgumentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArgumentServiceImpl implements IArgumentService{
    @Autowired
    ArgumentDao argumentDao;

    @Override
    public Long add(ArgumentAddReqDTO argumentAddReqDTO) {
        LambdaQueryWrapper<SysArgument> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysArgument::getName, argumentAddReqDTO.getName())
            .or()
            .eq(SysArgument::getConfigKey, argumentAddReqDTO.getConfigKey());
        if(argumentDao.selectOne(wrapper) != null){
            log.warn("参数名称/键冲突");
            return null;
        }

        SysArgument sysArgument = new SysArgument();
        sysArgument.setConfigKey(argumentAddReqDTO.getConfigKey());
        sysArgument.setName(argumentAddReqDTO.getName());
        sysArgument.setValue(argumentAddReqDTO.getValue());
        if(StringUtils.isNotBlank(argumentAddReqDTO.getRemark())){
            sysArgument.setRemark(argumentAddReqDTO.getRemark());
        }
        log.warn(sysArgument.getConfigKey());
        argumentDao.insert(sysArgument);

        return sysArgument.getId();
    }
}
