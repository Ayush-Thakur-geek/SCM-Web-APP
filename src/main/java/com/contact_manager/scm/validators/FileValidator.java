package com.contact_manager.scm.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("File is empty")
                    .addConstraintViolation();
            return false;
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("File size exceeds the limit of 2MB")
                    .addConstraintViolation();
            return false;
        }


        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
}
