package com.nexus.nexusadminservice.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeListReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeWriteReqDTO;
import com.nexus.nexusadminaqi.config.domain.vo.DictionaryTypeVO;
import com.nexus.nexusadminaqi.config.feign.ConfigFeignClient;
import com.nexus.nexusadminservice.config.service.impl.SysDictionaryTypeImpl;
import com.nexus.nexuscommondomain.domain.R;
import com.nexus.nexuscommondomain.domain.vo.BasePageVO;

@RestController
public class ConfigController implements ConfigFeignClient {
    @Autowired
    SysDictionaryTypeImpl sysDictionaryTypeImpl;

    @PostMapping("/dictionary_type/add")
    public R<Long> add_type(@RequestBody @Validated DictionaryTypeWriteReqDTO dictionaryTypeWriteReqDTO) {
        return R.ok(sysDictionaryTypeImpl.addType(dictionaryTypeWriteReqDTO));
    }

    @GetMapping("/dictionary_type/list")
    public R<BasePageVO<DictionaryTypeVO>> listType(DictionaryTypeListReqDTO DictionaryTypeListReqDTO){
        return R.ok(sysDictionaryTypeImpl.listType(DictionaryTypeListReqDTO));
    }
}
