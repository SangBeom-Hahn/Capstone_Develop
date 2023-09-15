package com.kyonggi.Capstone_Develop.domain.graduation;

import java.util.Arrays;

public enum Method {
    THESIS("논문"),
    OTHER("기타");
    
    private final String desc;
    
    Method(String desc) {
        this.desc = desc;
    }
    
    public static Method from(String desc) {
        return Arrays.stream(values())
                .filter(method -> method.desc.equals(desc))
                .findFirst()
                .orElseThrow();
    }
}
