package com.nexus.nexusadminservice.config.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexusadminaqi.config.domain.dto.DictionaryDataAddReqDTO;
import com.nexus.nexusadminaqi.config.domain.dto.DictionaryDataDTO;
import com.nexus.nexusadminaqi.config.feign.DictionaryFeignClient;
import com.nexus.nexusadminservice.config.service.impl.SysDictionaryTypeServiceImpl;
import com.nexus.nexuscommondomain.domain.R;

@RestController
public class DictionaryController implements DictionaryFeignClient {
    @Autowired
    SysDictionaryTypeServiceImpl sysDictionaryTypeServiceImpl;

    @Override
    public R<List<DictionaryDataDTO>> selectDictDataByType(String typeKey) {
        List<DictionaryDataDTO> list = sysDictionaryTypeServiceImpl.selectDictDataByType(typeKey);
        if(list == null){
            return R.fail("通过字典类型键获取字典数据失败 ");
        }
        return R.ok(list);
    }

    @Override
    public R<Map<String, List<DictionaryDataDTO>>> selectDictDatasByTypes(List<String> typeKeys) {
        Map<String, List<DictionaryDataDTO>> map = new HashMap<>();
        map = sysDictionaryTypeServiceImpl.selectDictsDataByTypes(typeKeys);
        if(map == null){
            return R.fail("通过多个字典类型键获取对应字典数据失败 ");
        }
        return R.ok(map);
    }

    @Override
    public R<DictionaryDataDTO> selectDictDataByKey(String dataKey) {
        DictionaryDataDTO ret = sysDictionaryTypeServiceImpl.selectDictDataByKey(dataKey);
        if(ret == null){
            return R.fail("通过字典数据键获取字典数据失败 ");
        }
        return R.ok(ret);
    }

    @Override
    public R<List<DictionaryDataDTO>> selectDictDatasByKeys(List<String> dataKeys) {
        List<DictionaryDataDTO> list = sysDictionaryTypeServiceImpl.selectDictDatasByKeys(dataKeys);
        if(list == null){
            return R.fail("通过多个字典数据键获取对应字典数据失败");
        }
        return R.ok(list);
    }

}
