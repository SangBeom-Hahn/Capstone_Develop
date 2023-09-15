package com.kyonggi.Capstone_Develop.domain.graduation;

public enum Step {
    PROPOSAL("제안서"),
    RECEIVED("신청접수"),
    INTERIM_REPORT("중간보고서"),
    FINAL_REPORT("최종보고서"),
    FINAL_PASS("최종통과"),
    OTHER_QUALIFICATIONS("기타자격");
    
    private final String desc;
    
    Step(String desc) {
        this.desc = desc;
    }
}
