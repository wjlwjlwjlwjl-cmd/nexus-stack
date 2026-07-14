package com.nexus.nexusadminservice.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryDataAddReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryDataListReqDTO;
import com.nexus.nexusadminaqi.config.domain.vo.DictionaryDataVO;
import com.nexus.nexusadminservice.config.dao.ConfigDataDao;
import com.nexus.nexusadminservice.config.dao.ConfigTypeDao;
import com.nexus.nexusadminservice.config.domain.entity.SysDictionaryData;
import com.nexus.nexusadminservice.config.domain.entity.SysDictionaryType;
import com.nexus.nexusadminservice.config.service.ISysDictionaryData;
import com.nexus.nexuscommoncore.utils.BeanCopyUtil;
import com.nexus.nexuscommondomain.domain.vo.BasePageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysDictionaryDataImpl implements ISysDictionaryData{
    @Autowired
    ConfigDataDao configDataDao;
    @Autowired
    ConfigTypeDao configTypeDao;

    @Override
    public Long addData(DictionaryDataAddReqDTO dictionaryDataAddReqDTO) {
        //表中有维护联合索引（键-值）
        //检查字典类型是否存在
        LambdaQueryWrapper<SysDictionaryType> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(SysDictionaryType::getTypeKey, dictionaryDataAddReqDTO.getTypeKey());
        if(configTypeDao.selectOne(wrapper1) == null){
            log.warn("[addData] TypeKey 不存在");
            return null;
        }

        //检查字典数据键或值是否存在
        LambdaQueryWrapper<SysDictionaryData> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(SysDictionaryData::getDataKey, dictionaryDataAddReqDTO.getDataKey())
            .eq(SysDictionaryData::getValue, dictionaryDataAddReqDTO.getValue());
        if(configDataDao.selectOne(wrapper2) != null){
            log.warn("[addData] 字典数据键或值已存在");            
            return null;
        }

        SysDictionaryData sysDictionaryData = new SysDictionaryData();
        sysDictionaryData.setDataKey(dictionaryDataAddReqDTO.getDataKey());
        sysDictionaryData.setTypeKey(dictionaryDataAddReqDTO.getTypeKey());
        sysDictionaryData.setValue(dictionaryDataAddReqDTO.getValue());
        if(StringUtils.isNotBlank(dictionaryDataAddReqDTO.getRemark())){
            sysDictionaryData.setRemark(dictionaryDataAddReqDTO.getRemark());
        }
        if(dictionaryDataAddReqDTO.getSort() != null){
            sysDictionaryData.setSort(dictionaryDataAddReqDTO.getSort());
        }
        configDataDao.insert(sysDictionaryData);
        return sysDictionaryData.getId();
    }

    @Override
    public BasePageVO<DictionaryDataVO> listData(DictionaryDataListReqDTO dictionaryDataListReqDTO) {
        BasePageVO<DictionaryDataVO> list = new BasePageVO<>();

        LambdaQueryWrapper<SysDictionaryData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictionaryData::getTypeKey, dictionaryDataListReqDTO.getTypeKey());
        if(StringUtils.isNotBlank(dictionaryDataListReqDTO.getValue())){
            wrapper.eq(SysDictionaryData::getValue, dictionaryDataListReqDTO.getValue());
        }
        Page<SysDictionaryData> page = configDataDao.selectPage(new Page<>(dictionaryDataListReqDTO.getPageNo().longValue(), dictionaryDataListReqDTO.getPageSize()), wrapper);

        list.setTotalPages(Integer.parseInt(String.valueOf(page.getPages())));
        list.setTotals(Integer.parseInt(String.valueOf(page.getTotal())));
        List<DictionaryDataVO> items = new ArrayList<>();
        for(SysDictionaryData sysDictionaryData: page.getRecords()){
            DictionaryDataVO dictionaryDataVO = new DictionaryDataVO();
            BeanCopyUtil.copyProperties(sysDictionaryData, dictionaryDataVO);
            items.add(dictionaryDataVO);
        }
        list.setList(items);
        return list;
    }

}
