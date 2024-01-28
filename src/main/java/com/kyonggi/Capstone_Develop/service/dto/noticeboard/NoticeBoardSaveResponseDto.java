package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.noticeboard.NoticeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeBoardSaveResponseDto {
    private Long id;

    List<String> uploadFileNames;

    List<String> storeFileNames;
    
    public static NoticeBoardSaveResponseDto of(
            NoticeBoard noticeBoard,
            List<String> uploadFileNames,
            List<String> storeFileNames
    ) {
        return new NoticeBoardSaveResponseDto(
                noticeBoard.getId(),
                uploadFileNames,
                storeFileNames
        );
    }
}
