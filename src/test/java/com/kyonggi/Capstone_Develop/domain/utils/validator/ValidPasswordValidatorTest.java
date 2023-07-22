package com.kyonggi.Capstone_Develop.domain.utils.validator;

import com.kyonggi.Capstone_Develop.utils.validator.ValidPasswordValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ValidPasswordValidatorTest {
    private ValidPasswordValidator validator;
    private ConstraintValidatorContext context;
    
    @BeforeEach
    public void setUp() {
        validator = new ValidPasswordValidator();
        
        context = mock(ConstraintValidatorContext.class);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"short", "password123", "AllLowercaseLetters", "alluppercaseletters", "1234567890", "!@#$%^&*()"})
    @DisplayName("형식에 맞지 않은 비밀번호가 입력되면 검증을 통과하지 못한다.")
    void invalidPassword(String password) {
        assertThat(validator.isValid(password, context))
                .isFalse();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Abcd123!@#", "P@ssw0rd"})
    @DisplayName("형식에 맞는 비밀번호가 입력되면 검증을 통과한다.")
    void validPassword(String password) {
        assertThat(validator.isValid(password, context))
                .isTrue();
    }
}
