package com.nexus.nexusfileservice.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.exceptions.ClientException;

import com.aliyun.oss.OSSClient;
import com.nexus.nexusfileservice.config.OSSAutoConfiguration;
import com.nexus.nexusfileservice.config.OSSProperties;
import com.nexus.nexusfileservice.domain.dto.FileDTO;
import com.nexus.nexusfileservice.domain.dto.SignDTO;
import com.nexus.nexusfileservice.service.impl.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings({"null"})
public class FileServiceImpl implements IFileService {
    @Autowired
    OSSAutoConfiguration ossAutoConfiguration;

    @Autowired
    OSSProperties ossProperties;    //从 Nacos 中读取的 oss 配置信息

    public FileDTO upload(MultipartFile file) {
        FileDTO fileDTO = new FileDTO();

        try{
            String originalFileName = file.getOriginalFilename();
            String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
            String objectName = ossProperties.getPathPrefix() + UUID.randomUUID() + '.' + extName;

            String bucketName = ossProperties.getBucketName();

            OSSClient client = ossAutoConfiguration.ossClient(ossProperties);
            client.putObject(bucketName, objectName, new ByteArrayInputStream(file.getBytes()));

            fileDTO.setKey(objectName);
            fileDTO.setUrl(ossProperties.getBaseUrl());
            fileDTO.setName(new File(objectName).getName());
        }
        catch (ClientException e) {
            log.warn("[oss] upload fail: ", e);       
        }
        catch(IOException e){
            log.warn("[oss] MultipartFile get byte fail: ", e);
        }

        return fileDTO;
    }

    public SignDTO getSign() {
        return null;
    }
}
