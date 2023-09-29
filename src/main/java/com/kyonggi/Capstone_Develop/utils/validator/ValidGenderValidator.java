package com.kyonggi.Capstone_Develop.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidGenderValidator implements ConstraintValidator<ValidGender, String> {
    private static final List<String> ALLOWED_GENDERS = List.of("남자", "여자", "Admin");
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ALLOWED_GENDERS.contains(value);
    }
}
