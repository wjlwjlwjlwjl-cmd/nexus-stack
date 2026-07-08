package com.fileservice.service;

import com.fileservice.domain.dto.FileDTO;
import com.fileservice.domain.dto.SignDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    FileDTO upload(MultipartFile file);

    SignDTO getSign();
}
