package com.kyonggi.Capstone_Develop.domain.graduation;

import java.util.Arrays;

public enum Step {
    RECEIVED("신청접수", null),
    PROPOSAL("제안서", Step.RECEIVED),
    INTERIM_REPORT("중간보고서", Step.PROPOSAL),
    FINAL_REPORT("최종보고서", Step.INTERIM_REPORT),
    FINAL_PASS("최종통과", Step.FINAL_REPORT),
    OTHER_QUALIFICATIONS("기타자격", null);
    
    private final String desc;
    
    private final Step currentStep;
    
    Step(String desc, Step currentStep) {
        this.desc = desc;
        this.currentStep = currentStep;
    }
    
    public static Step from(Step step) {
        return Arrays.stream(values())
                .filter(wantStep -> wantStep.currentStep.equals(step))
                .findFirst()
                .orElseThrow();
    }
}
