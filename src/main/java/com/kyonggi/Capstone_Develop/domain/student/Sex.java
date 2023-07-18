package com.kyonggi.Capstone_Develop.domain.student;

import lombok.Getter;

@Getter
public enum Sex {
    MAIL("남"),
    FEMAIL("여");
    
    private final String desc;
    
    Sex(String desc) {
        this.desc = desc;
    }
}
