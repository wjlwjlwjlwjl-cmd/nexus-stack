package com.nexus.nexusadminaqi.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.nexus.nexusadminaqi.domain.RegionVO;
import com.nexus.nexuscommondomain.domain.R;

@FeignClient(contextId = "mapFeignClient", value = "nexus-admin") //声明 HTTP 远程调用
public interface MapFeignClient {
    @GetMapping("/map/get_list")
    public R<List<RegionVO>> get_list();

    @GetMapping("/map/get_py_list")
    public R<Map<String, List<RegionVO>>> get_py_list();

    @GetMapping("/map/get_child_list")
    public R<List<RegionVO>> get_child_list(int parentId);
}