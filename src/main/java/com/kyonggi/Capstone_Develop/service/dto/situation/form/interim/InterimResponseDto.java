package com.kyonggi.Capstone_Develop.service.dto.situation.form.interim;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.situation.Interim;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterimResponseDto {
    private Long interimId;
    
    private Long applyId;
    
    private String title;
    
    private String division;
    
    private String text;
    
    private String plan;
    
    private String rejectReason;
    
    public static InterimResponseDto of(Interim interim, Apply apply) {
        return new InterimResponseDto(
                interim.getId(),
                apply.getId(),
                interim.getTitle(),
                interim.getDivision(),
                interim.getText(),
                interim.getPlan(),
                interim.getRejectReason()
        );
    }
}
