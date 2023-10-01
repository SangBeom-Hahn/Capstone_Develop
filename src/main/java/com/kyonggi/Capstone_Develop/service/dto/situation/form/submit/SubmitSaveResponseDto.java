package com.kyonggi.Capstone_Develop.service.dto.situation.form.submit;

import com.kyonggi.Capstone_Develop.domain.situation.Submit;
import lombok.Getter;

@Getter
public class SubmitSaveResponseDto {
    private Long id;
    
    public SubmitSaveResponseDto(final Long id) {
        this.id = id;
    }
    
    public static SubmitSaveResponseDto from(final Submit submit) {
        return new SubmitSaveResponseDto(submit.getId());
    }
}
