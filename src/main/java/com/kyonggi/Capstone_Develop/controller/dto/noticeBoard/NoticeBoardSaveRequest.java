package com.kyonggi.Capstone_Develop.controller.dto.noticeBoard;

import com.kyonggi.Capstone_Develop.service.dto.noticeboard.NoticeBoardSaveRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NoticeBoardSaveRequest {
    @Min(1)
    @Max(255)
    @NotBlank(message = EMPTY_MESSAGE)
    private String content;
    
    @NotNull(message = EMPTY_MESSAGE)
    private Boolean fix;
    
    @Min(1)
    @Max(255)
    @NotBlank(message = EMPTY_MESSAGE)
    private String title;
    
    @NotNull(message = EMPTY_MESSAGE)
    private Integer views;
    
    public NoticeBoardSaveRequestDto toServiceDto() {
        return new NoticeBoardSaveRequestDto(
                content,
                fix,
                title,
                views
        );
    }
}
