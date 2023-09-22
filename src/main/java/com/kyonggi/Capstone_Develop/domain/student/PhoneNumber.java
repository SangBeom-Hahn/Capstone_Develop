package com.kyonggi.Capstone_Develop.domain.student;

import com.kyonggi.Capstone_Develop.exception.InvalidPhoneNumberFormatException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PhoneNumber {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
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
        Matcher matcher = EMAIL_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new InvalidPhoneNumberFormatException(value);
        }
    }
}
