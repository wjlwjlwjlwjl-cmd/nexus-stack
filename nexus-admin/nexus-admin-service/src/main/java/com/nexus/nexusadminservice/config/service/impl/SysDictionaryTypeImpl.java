package com.nexus.nexusadminservice.config.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeWriteReqDTO;
import com.nexus.nexusadminservice.config.dao.ConfigDao;
import com.nexus.nexusadminservice.config.domain.entity.SysDictionaryType;
import com.nexus.nexusadminservice.config.service.ISysDictionaryType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysDictionaryTypeImpl implements ISysDictionaryType {
    @Autowired
    ConfigDao configDao;

    @Override
    public Long add_type(DictionaryTypeWriteReqDTO dictionaryTypeWriteReqDTO){
        LambdaQueryWrapper<SysDictionaryType> wrapper = new LambdaQueryWrapper<>();
        
        //字典类型键、值有一个冲突都不能插入
        wrapper.select(SysDictionaryType::getId)
            .eq(SysDictionaryType::getTypeKey, dictionaryTypeWriteReqDTO.getTypeKey())
            .or()
            .eq(SysDictionaryType::getValue, dictionaryTypeWriteReqDTO.getValue());
        SysDictionaryType sysDictionaryType = configDao.selectOne(wrapper);
        if(sysDictionaryType != null){
            log.warn("字典类型键或值冲突");
            return null;
        }

        sysDictionaryType = new SysDictionaryType();
        sysDictionaryType.setRemark(dictionaryTypeWriteReqDTO.getRemark());
        sysDictionaryType.setStatus(1);
        sysDictionaryType.setValue(dictionaryTypeWriteReqDTO.getValue());
        sysDictionaryType.setTypeKey(dictionaryTypeWriteReqDTO.getTypeKey());
        configDao.insert(sysDictionaryType);
        return sysDictionaryType.getId();
    }
}
