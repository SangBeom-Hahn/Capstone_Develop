package com.kyonggi.Capstone_Develop.domain.refreshtoken;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    private static final int EXPIRED_DAYS = 7;
    private static final int REMAINING_DAYS_TO_EXTENDS = 2;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;
    
    @Column(name = "token_value", unique = true, nullable = false)
    private String tokenValue;
    
    @Column(name = "member_id", nullable = false)
    private Long memberId;
    
    @Column(name = "expired_time")
    private LocalDateTime expiredTime;
    
    private RefreshToken(final String tokenValue, final Long memberId, final LocalDateTime expiredTime) {
        this.tokenValue = tokenValue;
        this.memberId = memberId;
        this.expiredTime = expiredTime;
    }
    
    public static RefreshToken createBy(final Long memberId, final RefreshTokenGenerator generator) {
        return new RefreshToken(
                generator.generate(),
                memberId,
                LocalDateTime.now().plusDays(EXPIRED_DAYS)
        );
    }

    public boolean isExpired() {
        return expiredTime.isBefore(LocalDateTime.now());
    }

    public void extendsExpired() {
        final LocalDateTime now = LocalDateTime.now();
        if (expiredTime.isBefore(now.plusDays(REMAINING_DAYS_TO_EXTENDS))) {
            expiredTime = now.plusDays(EXPIRED_DAYS);
        }
    }
}
