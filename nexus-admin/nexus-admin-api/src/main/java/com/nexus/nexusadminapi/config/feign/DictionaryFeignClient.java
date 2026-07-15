package com.nexus.nexusadminapi.config.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nexus.nexusadminapi.config.domain.dto.DictionaryDataDTO;
import com.nexus.nexuscommondomain.domain.R;

@FeignClient(value = "dictionaryFeightClient", contextId = "nexus-admin")
public interface DictionaryFeignClient {
    @GetMapping("/dictionary_data/type")
    public R<List<DictionaryDataDTO>> selectDictDataByType(@RequestParam String typeKey);

    @GetMapping("/dictionary_data/types")
    public R<Map<String, List<DictionaryDataDTO>>> selectDictDatasByTypes(@RequestParam List<String> typeKeys);

    @GetMapping("/dictionary_data/key")
    public R<DictionaryDataDTO> selectDictDataByKey(@RequestParam String dataKey);

    @GetMapping("/dictionary_data/keys")
    public R<List<DictionaryDataDTO>> selectDictDatasByKeys(@RequestParam List<String> dataKeys);
}
