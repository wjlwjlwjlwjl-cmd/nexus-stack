package com.nexus.nexusfileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nexus.nexuscommoncore.utils.BeanCopyUtil;
import com.nexus.nexuscommondomain.domain.R;
import com.nexus.nexusfileservice.domain.dto.FileDTO;
import com.nexus.nexusfileservice.domain.dto.SignDTO;
import com.nexus.nexusfileservice.domain.vo.FileVO;
import com.nexus.nexusfileservice.domain.vo.SignVO;
import com.nexus.nexusfileservice.service.FileServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings({"null"})
public class FileController {
    @Autowired    
    FileServiceImpl fileService;

    /**
     * 
     * @param   file 要上传的文件
     * @return  上传结果
     */
    @PostMapping("/upload")
    public R<FileVO> upload(MultipartFile file){
        FileDTO fileDTO = fileService.upload(file);
        FileVO fileVO = new FileVO();
        BeanCopyUtil.copyProperties(fileDTO, fileVO);
        return R.ok(fileVO);
    }

    /**
     * 获取签名
     * 
     * @return   获取结果
     */
    @RequestMapping("sign")
    public R<SignVO> getSign(){
        SignDTO signDTO = fileService.getSign();
        SignVO signVO = new SignVO();
        BeanCopyUtil.copyProperties(signDTO, signVO);
        return R.ok(signVO);
    }
}
