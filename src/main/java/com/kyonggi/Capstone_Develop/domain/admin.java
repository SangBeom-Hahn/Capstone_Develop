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
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
