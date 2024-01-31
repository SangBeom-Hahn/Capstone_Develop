package com.kyonggi.Capstone_Develop.controller.dto.noticeBoard;

import com.kyonggi.Capstone_Develop.service.dto.noticeboard.NoticeBoardUpdateRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardUpdateRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String content;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String title;
    
    public NoticeBoardUpdateRequestDto toServiceDto() {
        return new NoticeBoardUpdateRequestDto(
                content,
                title
        );
    }
}
