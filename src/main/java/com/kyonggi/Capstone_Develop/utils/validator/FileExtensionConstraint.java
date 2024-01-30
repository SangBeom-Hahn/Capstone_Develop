package com.kyonggi.Capstone_Develop.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileExtensionConstraintValidator.class)
public @interface FileExtensionConstraint {
    String message() default "파일은 hwp만 허용합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
