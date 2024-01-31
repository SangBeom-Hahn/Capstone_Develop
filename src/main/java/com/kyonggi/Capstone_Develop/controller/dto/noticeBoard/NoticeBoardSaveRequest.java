package com.kyonggi.Capstone_Develop.controller.dto.noticeBoard;

import com.kyonggi.Capstone_Develop.service.dto.noticeboard.NoticeBoardSaveRequestDto;
import com.kyonggi.Capstone_Develop.utils.validator.FileExtensionConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NoticeBoardSaveRequest {
    @Size(min = 1, max = 255)
    @NotBlank(message = EMPTY_MESSAGE)
    private String content;
    
    @NotNull(message = EMPTY_MESSAGE)
    private Boolean fix;
    
    @Size(min = 1, max = 255)
    @NotBlank(message = EMPTY_MESSAGE)
    private String title;
    
    @NotNull(message = EMPTY_MESSAGE)
    private Integer views;

    @FileExtensionConstraint
    private List<MultipartFile> files;
    
    public NoticeBoardSaveRequestDto toServiceDto() {
        return new NoticeBoardSaveRequestDto(
                content,
                fix,
                title,
                views,
                files
        );
    }
}
