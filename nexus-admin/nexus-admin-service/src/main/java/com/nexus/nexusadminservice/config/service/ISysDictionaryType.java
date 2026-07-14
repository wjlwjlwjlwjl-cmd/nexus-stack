package com.nexus.nexusadminservice.config.service;

import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeListReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeWriteReqDTO;
import com.nexus.nexusadminaqi.config.domain.vo.DictionaryTypeVO;
import com.nexus.nexuscommondomain.domain.vo.BasePageVO;

public interface ISysDictionaryType {
    public Long addType(DictionaryTypeWriteReqDTO dictionaryTypeWriteReqDTO);
    public BasePageVO<DictionaryTypeVO> listType(DictionaryTypeListReqDTO dictionaryTypeListReqDTO);
}