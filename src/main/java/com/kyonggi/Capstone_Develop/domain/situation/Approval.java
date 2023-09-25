package com.kyonggi.Capstone_Develop.domain.situation;

public enum Approval {
    APPROVAL("승인"),
    UNAPPROVAL("미승인"),
    REJECT("반려");
    
    private final String desc;
    
    Approval(String desc) {
        this.desc = desc;
    }
}
