package com.nexus.nexusadminservice.config.service;

import com.nexus.nexusadminaqi.config.domain.dto.DictionaryDataAddReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryDataListReqDTO;
import com.nexus.nexusadminaqi.config.domain.vo.DictionaryDataVO;
import com.nexus.nexuscommondomain.domain.vo.BasePageVO;

public interface ISysDictionaryData {
    /**
     * 添加字典数据
     * 
     * @param dictionaryDataAddReqDTO
     * @return  字典数据 ID
     */
    public Long addData(DictionaryDataAddReqDTO dictionaryDataAddReqDTO);

    /**
     * 根据 TypeKey 查找字典数据
     * 
     * @param dictionaryDataListReqDTO
     * @return  字典数据列表
     */
    public BasePageVO<DictionaryDataVO> listData(DictionaryDataListReqDTO dictionaryDataListReqDTO);
} 