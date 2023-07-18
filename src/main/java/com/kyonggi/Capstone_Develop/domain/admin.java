package com.kyonggi.Capstone_Develop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "admin")
public class admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;
    
    @Column(name = "login_id", length = 255, nullable = false)
    private String loginId;
    
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    
    public admin(String loginId, String password, String name) {
        validateLoginId(loginId);
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
    
    private void validateLoginId(String loginId) {
        validateLoginIdSize(loginId);
        validateLoginIdHasSpecialCharacter(loginId);
    }
    
    private void validateLoginIdSize(String loginId) {
        if (isValidSize(loginId)) {
            throw new IllegalArgumentException(loginId);
        }
    }
    
    private static boolean isValidSize(String loginId) {
        return loginId.length() < 2 && loginId.length() > 16;
    }
    
    private void validateLoginIdHasSpecialCharacter(String loginId) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{0,7}$");
        Matcher matcher = pattern.matcher(loginId);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(loginId);
        }
    }
}
