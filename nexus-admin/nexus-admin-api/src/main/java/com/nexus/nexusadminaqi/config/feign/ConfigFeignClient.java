package com.nexus.nexusadminaqi.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nexus.nexusadminaqi.config.domain.dto.DictionaryTypeWriteReqDTO;
import com.nexus.nexusadminaqi.config.domain.vo.SysDictionaryTypeVO;

@FeignClient(value = "configFeignClient", contextId = "nexus-config")
public interface ConfigFeignClient {
}
