package com.nexus.nexusadminservice.argument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexusadminaqi.argument.domain.dto.ArgumentAddReqDTO;
import com.nexus.nexusadminaqi.argument.feign.ArgumentFeignClient;
import com.nexus.nexusadminservice.argument.service.impl.ArgumentServiceImpl;
import com.nexus.nexuscommondomain.domain.R;

@RestController
public class ArgumentController implements ArgumentFeignClient{
    @Autowired
    ArgumentServiceImpl argumentServiceImpl;

    @Override
    public R<Long> add(ArgumentAddReqDTO argumentAddReqDTO) {
        Long ret = argumentServiceImpl.add(argumentAddReqDTO);
        if(ret == null){
            return R.fail("新建参数失败");
        }
        return R.ok(ret);
    }
}
