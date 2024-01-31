package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.noticeboard.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileResponseDto {
    private Long id;

    private String uploadFileName;

    public static FileResponseDto from(UploadFile uploadFile) {
        return new FileResponseDto(uploadFile.getId(), uploadFile.getUploadFileName());
    }
}
