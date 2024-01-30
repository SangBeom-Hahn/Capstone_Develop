package com.kyonggi.Capstone_Develop.controller.dto.noticeBoard;

import com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardDownloadRequest {
    private Long uploadFileId;

    @NotBlank(message = ValidateMessage.EMPTY_MESSAGE)
    private String uploadFileName;
}
