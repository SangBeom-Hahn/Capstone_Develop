package com.kyonggi.Capstone_Develop.controller.dto.situation.proposal;

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
public class ProposalSaveRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String title;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String division;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String keyword;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String content;
    
    public ProposalSaveRequestDto toServiceDto() {
        return new ProposalSaveRequestDto(
                title,
                division,
                keyword,
                content
        );
    }
}
