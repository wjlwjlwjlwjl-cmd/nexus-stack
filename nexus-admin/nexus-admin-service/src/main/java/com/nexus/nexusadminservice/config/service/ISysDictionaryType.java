package com.nexus.nexusadminservice.config.service;

import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeWriteReqDTO;

public interface ISysDictionaryType {
    public Long add_type(DictionaryTypeWriteReqDTO dictionaryTypeWriteReqDTO);
}