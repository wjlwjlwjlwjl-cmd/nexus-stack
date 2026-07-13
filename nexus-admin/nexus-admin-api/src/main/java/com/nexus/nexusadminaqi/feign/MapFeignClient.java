package com.nexus.nexusadminaqi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.nexus.nexusadminaqi.domain.RegionVO;
import com.nexus.nexuscommondomain.domain.R;

@FeignClient(contextId = "mapFeignClient", value = "nexus-admin") //声明 HTTP 远程调用
public interface MapFeignClient {
    @GetMapping("/map/get_list")
    public R<List<RegionVO>> get_list();
}