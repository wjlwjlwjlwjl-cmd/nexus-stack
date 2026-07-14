package com.nexus.nexusadminservice.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeListReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeWriteReqDTO;
import com.nexus.nexusadminaqi.config.domain.vo.DictionaryTypeVO;
import com.nexus.nexusadminservice.config.dao.ConfigDao;
import com.nexus.nexusadminservice.config.domain.entity.SysDictionaryType;
import com.nexus.nexusadminservice.config.service.ISysDictionaryType;
import com.nexus.nexuscommoncore.utils.BeanCopyUtil;
import com.nexus.nexuscommondomain.domain.vo.BasePageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings({"null"})
public class SysDictionaryTypeImpl implements ISysDictionaryType {
    @Autowired
    ConfigDao configDao;

    @Override
    public Long addType(DictionaryTypeWriteReqDTO dictionaryTypeWriteReqDTO){
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

    @Override
    public BasePageVO<DictionaryTypeVO> listType(DictionaryTypeListReqDTO dictionaryTypeListReqDTO) {
        LambdaQueryWrapper<SysDictionaryType> wrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(dictionaryTypeListReqDTO.getTypeKey())){
            wrapper.eq(SysDictionaryType::getTypeKey, dictionaryTypeListReqDTO.getTypeKey());
        }
        if(StringUtils.isNotBlank(dictionaryTypeListReqDTO.getValue())){
            wrapper.likeRight(SysDictionaryType::getValue, dictionaryTypeListReqDTO.getValue());
        }

        BasePageVO<DictionaryTypeVO> result = new BasePageVO<>();
        //按照页数查询
        Page<SysDictionaryType> page = configDao.selectPage(
            new Page<>(dictionaryTypeListReqDTO.getPageNo().longValue(), dictionaryTypeListReqDTO.getPageSize().longValue())
            , wrapper);
        result.setTotalPages(Integer.parseInt(String.valueOf(page.getPages())));
        result.setTotals(Integer.parseInt(String.valueOf(page.getTotal())));

        List<DictionaryTypeVO> list = new ArrayList<>();
        for(SysDictionaryType sysDictionaryType: page.getRecords()){
            DictionaryTypeVO dictionaryTypeVO = new DictionaryTypeVO();
            BeanCopyUtil.copyProperties(sysDictionaryType, dictionaryTypeVO);
            list.add(dictionaryTypeVO);
        }
        result.setList(list);
        return result;
    }
}
