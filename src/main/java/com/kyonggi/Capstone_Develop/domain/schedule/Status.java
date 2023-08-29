package com.kyonggi.Capstone_Develop.domain.schedule;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Arrays;

@Getter
public enum Status {
    WAIT("대기", (now, startDate, endDate) -> now.isBefore(startDate)),
    END("마감", (now, startDate, endDate) -> now.isAfter(endDate)),
    PROCEEDING("진행중", (now, startDate, endDate) -> now.isAfter(startDate) && now.isBefore(endDate));
    
    private final String desc;
    
    private final TriPredicate<LocalDate, LocalDate, LocalDate> expression;
    
    Status(String desc, TriPredicate<LocalDate, LocalDate, LocalDate> expression) {
        this.desc = desc;
        this.expression = expression;
    }
    
    public static Status of(LocalDate now, LocalDate startDate, LocalDate endDate) {
        return Arrays.stream(values())
                .filter(status -> status.expression.test(now, startDate, endDate))
                .findFirst()
                .orElse(PROCEEDING);
    }
}
