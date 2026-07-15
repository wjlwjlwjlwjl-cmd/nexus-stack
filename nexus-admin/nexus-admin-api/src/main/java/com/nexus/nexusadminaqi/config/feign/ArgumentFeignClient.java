package com.nexus.nexusadminaqi.config.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.nexus.nexusadminaqi.config.domain.dto.ArgumentAddReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.ArgumentDTO;
import com.nexus.nexusadminaqi.config.domain.dto.ArgumentEditReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.ArgumentListReqDTO;
import com.nexus.nexusadminaqi.config.domain.vo.ArgumentVO;
import com.nexus.nexuscommondomain.domain.R;
import com.nexus.nexuscommondomain.domain.vo.BasePageVO;

@FeignClient(value = "argumentFeignClient", contextId = "nexus-admin")
public interface ArgumentFeignClient {
    @PostMapping("/argument/add")
    R<Long> add(@RequestBody @Validated ArgumentAddReqDTO argumentAddReqDTO);

    @GetMapping("/argument/list")
    R<BasePageVO<ArgumentVO>> list(@Validated ArgumentListReqDTO argumentListReqDTO);

    @PostMapping("/argument/edit")
    R<Long> edit(@RequestBody @Validated ArgumentEditReqDTO argumentEditReqDTO);

    @GetMapping("/key")
    ArgumentDTO getByConfigKey(@RequestParam String configKey);

    @GetMapping("/keys")
    List<ArgumentDTO> getByConfigKeys(@RequestParam List<String> configKeys);
}
