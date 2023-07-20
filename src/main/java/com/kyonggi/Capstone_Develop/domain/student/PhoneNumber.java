package com.kyonggi.Capstone_Develop.domain.student;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PhoneNumber {
    @Column(name = "phone_number", length = 255, nullable = false)
    private String value;
    
    private PhoneNumber(String value) {
        this.value = value;
    }
    
    public static PhoneNumber from(String value) {
        validatePhoneNumber(value);
        return new PhoneNumber(value);
    }
    
    private static void validatePhoneNumber(String value) {
        Pattern pattern = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(value);
        }
    }
}
