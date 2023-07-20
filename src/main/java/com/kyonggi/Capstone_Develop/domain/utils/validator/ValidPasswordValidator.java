package com.kyonggi.Capstone_Develop.domain.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 16;
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{" + MIN_SIZE + "," + MAX_SIZE + "}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Override
    public void initialize(ValidPassword constraint) {
    }
    
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
