package com.nexus.nexusadminservice.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeWriteReqDTO;
import com.nexus.nexusadminaqi.config.feign.ConfigFeignClient;
import com.nexus.nexusadminservice.config.service.impl.SysDictionaryTypeImpl;
import com.nexus.nexuscommondomain.domain.R;

@RestController
public class ConfigController implements ConfigFeignClient {
    @Autowired
    SysDictionaryTypeImpl sysDictionaryTypeImpl;

    @PostMapping("dictionary_type/add")
    public R<Long> add_type(@RequestBody @Validated DictionaryTypeWriteReqDTO dictionaryTypeWriteReqDTO) {
        return R.ok(sysDictionaryTypeImpl.add_type(dictionaryTypeWriteReqDTO));
    }

}
