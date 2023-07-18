package com.kyonggi.Capstone_Develop.domain.student;

import lombok.Getter;

import javax.annotation.processing.Generated;

@Getter
public enum Grade {
    ONE("1th Grade"),
    SECEOND("2th Grade"),
    THIRD("3th Grade"),
    FOURTH("4th Grade");
    
    private final String desc;
    
    Grade(String desc) {
        this.desc = desc;
    }
}
