package com.contact_manager.scm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
    String uploadImg(MultipartFile file);
}
