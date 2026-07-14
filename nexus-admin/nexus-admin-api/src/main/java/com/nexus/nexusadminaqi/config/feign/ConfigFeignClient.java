package com.nexus.nexusadminaqi.config.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "configFeignClient", contextId = "nexus-config")
public interface ConfigFeignClient {
}
