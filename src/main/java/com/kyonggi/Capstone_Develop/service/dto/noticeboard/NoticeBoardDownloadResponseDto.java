package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.noticeboard.UploadFile;
import lombok.*;
import org.springframework.core.io.UrlResource;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardDownloadResponseDto {
    private UrlResource urlResource;

    public static NoticeBoardDownloadResponseDto from(UploadFile uploadFile) {
        return new NoticeBoardDownloadResponseDto(uploadFile.findResource());
    }
}
