package com.kyonggi.Capstone_Develop.controller.dto.situation.submit;

import com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitSaveRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitSaveRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String professorName;
    
    @NotNull(message = EMPTY_MESSAGE)
    @DateTimeFormat(pattern = ValidateMessage.DATE_FORMAT)
    private LocalDate graduationDate;
    
    @NotNull(message = EMPTY_MESSAGE)
    private Boolean capstoneCompletion;
    
    public SubmitSaveRequestDto toServiceDto() {
        return new SubmitSaveRequestDto(
                professorName,
                graduationDate,
                capstoneCompletion
        );
    }
}
