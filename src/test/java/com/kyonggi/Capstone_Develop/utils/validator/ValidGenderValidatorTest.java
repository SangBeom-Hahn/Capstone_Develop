package com.kyonggi.Capstone_Develop.utils.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ValidGenderValidatorTest {
    private ValidGenderValidator validator;
    
    private ConstraintValidatorContext context;
    
    @BeforeEach
    public void setUp() {
        validator = new ValidGenderValidator();
        context = mock(ConstraintValidatorContext.class);
    }
    
    @Test
    @DisplayName("허용하지 않는 성별이 들어오면 validator를 통과하지 못한다")
    void notAllowedGender() {
        // given
        final String unknown = "unknown";
        
        // when
        boolean actual = validator.isValid(unknown, context);
        
        // then
        assertThat(actual).isFalse();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"남자", "여자", "Admin"})
    @DisplayName("허용된 성별이 입력되면 검증을 통과한다.")
    void validPassword(String password) {
        assertThat(validator.isValid(password, context))
                .isTrue();
    }
}