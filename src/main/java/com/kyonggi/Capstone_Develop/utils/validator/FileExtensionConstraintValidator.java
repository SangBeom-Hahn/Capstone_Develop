package com.kyonggi.Capstone_Develop.utils.validator;

import com.kyonggi.Capstone_Develop.domain.file.FileExtension;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileExtensionConstraintValidator implements ConstraintValidator<FileExtensionConstraint, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true;
        }

        return FileExtension.isValidExtension(file.getOriginalFilename());
    }
}
