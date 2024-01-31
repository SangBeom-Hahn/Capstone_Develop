package com.kyonggi.Capstone_Develop.utils.validator;

import com.kyonggi.Capstone_Develop.domain.file.FileExtension;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class FileExtensionConstraintValidator implements ConstraintValidator<FileExtensionConstraint, List<MultipartFile>> {
    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext context) {
        if (files == null || files.isEmpty()) {
            return true;
        }

        return files.stream()
                .anyMatch(
                        file -> FileExtension.isValidExtension(file.getOriginalFilename())
                );
    }
}
