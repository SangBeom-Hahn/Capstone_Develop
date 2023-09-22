package com.kyonggi.Capstone_Develop.domain.student;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Sex {
    MALE("남자"),
    FEMALE("여자"),
    NONE("Admin");
    
    private final String desc;
    
    Sex(String desc) {
        this.desc = desc;
    }
    
    public static Sex from(String desc) {
        return Arrays.stream(values())
                .filter(sex -> sex.desc.equals(desc))
                .findFirst()
                .orElse(NONE);
    }
}
