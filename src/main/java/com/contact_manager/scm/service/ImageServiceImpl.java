package com.contact_manager.scm.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {
    @Override
    public String uploadImg(MultipartFile file) {
        return "";
    }
}
