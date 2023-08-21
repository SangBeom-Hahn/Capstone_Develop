package com.kyonggi.Capstone_Develop.domain.student;

import lombok.Getter;

@Getter
public enum Sex {
    MALE("남"),
    FEMALE("여"),
    NONE("Admin");
    
    private final String desc;
    
    Sex(String desc) {
        this.desc = desc;
    }
}
