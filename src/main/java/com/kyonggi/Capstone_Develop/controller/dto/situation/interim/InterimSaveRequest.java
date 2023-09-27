package com.kyonggi.Capstone_Develop.controller.dto.situation.interim;

import com.kyonggi.Capstone_Develop.service.dto.situation.form.interim.InterimSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalSaveRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterimSaveRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String title;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String division;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String text;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String plan;
    
    public InterimSaveRequestDto toServiceDto() {
        return new InterimSaveRequestDto(
                title,
                division,
                text,
                plan
        );
    }
}
