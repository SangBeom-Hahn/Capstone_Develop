package com.kyonggi.Capstone_Develop.domain.student;

import lombok.Getter;

@Getter
public enum Sex {
    MALE("남"),
    FEMALE("여");
    
    private final String desc;
    
    Sex(String desc) {
        this.desc = desc;
    }
}
