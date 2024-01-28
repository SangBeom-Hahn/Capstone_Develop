package com.kyonggi.Capstone_Develop.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class RawFileData {
    private String uploadFileName;
    private String storeFileName;
    private MultipartFile multipartFile;
}
