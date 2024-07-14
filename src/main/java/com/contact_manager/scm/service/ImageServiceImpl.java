package com.contact_manager.scm.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImg(MultipartFile contactImg, String fileName) {
        try {
            byte[] data = new byte[contactImg.getInputStream().available()];
            contactImg.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", fileName
            ));
            return this.getUrlFromPublicId(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
                .url()
                .transformation(
                        new Transformation()
                                .width(500)
                                .height(500)
                                .crop("fill")
                )
                .generate(publicId);
    }
}
