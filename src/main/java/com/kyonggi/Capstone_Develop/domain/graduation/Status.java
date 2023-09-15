package com.kyonggi.Capstone_Develop.domain.graduation;

public enum Status {
    APPROVAL("승인"),
    UNAPPROVAL("미승인");
    
    private final String desc;
    
    Status(String desc) {
        this.desc = desc;
    }
}
