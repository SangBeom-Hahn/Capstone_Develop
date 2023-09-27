package com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProposalSaveRequestDto {
    private String title;
    
    private String division;
    
    private String keyword;
    
    private String content;
}
