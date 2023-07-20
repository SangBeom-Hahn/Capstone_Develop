package com.kyonggi.Capstone_Develop.domain.student;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    @Column(name = "email", length = 255, nullable = false)
    String value;
    
    private Email(String value) {
        this.value = value;
    }
    
    public static Email from(String value) {
        validateEmail(value);
        return new Email(value);
    }
    
    private static void validateEmail(String value) {
        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(value);
        }
    }
}
