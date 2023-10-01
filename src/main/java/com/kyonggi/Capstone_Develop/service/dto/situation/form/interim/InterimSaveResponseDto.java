package com.kyonggi.Capstone_Develop.service.dto.situation.form.interim;

import com.kyonggi.Capstone_Develop.domain.situation.Interim;
import lombok.Getter;

@Getter
public class InterimSaveResponseDto {
    private Long id;
    
    public InterimSaveResponseDto(final Long id) {
        this.id = id;
    }
    
    public static InterimSaveResponseDto from(final Interim interim) {
        return new InterimSaveResponseDto(interim.getId());
    }
}
