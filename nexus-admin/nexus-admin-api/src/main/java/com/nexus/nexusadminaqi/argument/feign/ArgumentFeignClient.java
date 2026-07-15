package com.nexus.nexusadminaqi.argument.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nexus.nexusadminaqi.argument.domain.dto.ArgumentAddReqDTO;
import com.nexus.nexuscommondomain.domain.R;

@FeignClient(value = "argumentFeignClient", contextId = "nexus-admin")
public interface ArgumentFeignClient {
    @PostMapping("/argument/add")
    R<Long> add(@RequestBody @Validated ArgumentAddReqDTO argumentAddReqDTO);
}
