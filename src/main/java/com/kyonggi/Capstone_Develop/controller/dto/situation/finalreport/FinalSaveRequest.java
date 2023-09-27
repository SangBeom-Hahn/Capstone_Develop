package com.kyonggi.Capstone_Develop.controller.dto.situation.finalreport;

import com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport.FinalSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalSaveRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FinalSaveRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String title;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String division;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String qualification;
    
    @NotNull(message = EMPTY_MESSAGE)
    private Integer pageNumber;
    
    public FinalSaveRequestDto toServiceDto() {
        return new FinalSaveRequestDto(
                title,
                division,
                qualification,
                pageNumber
        );
    }
}
