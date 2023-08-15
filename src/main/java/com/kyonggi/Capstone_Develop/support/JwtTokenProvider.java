package com.kyonggi.Capstone_Develop.support;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    
    private final SecretKey key;
    private final long validityInMilliseconds;
    
    public JwtTokenProvider(@Value("${jwt.token.secret-key}") final String secretKey,
                            @Value("${jwt.token.expire-length}") final long validityInMilliseconds) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = validityInMilliseconds;
    }
    
    public String createToken(final String payload) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);
        
        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    public String getPayload(final String token) {
        return tokenToJws(token).getBody().getSubject();
    }
    
    public void validateAbleToken(final String token) {
        try {
            final Jws<Claims> claims = tokenToJws(token);
            
            validateExpiredToken(claims);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("D");
        }
    }
    
    private Jws<Claims> tokenToJws(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (final IllegalArgumentException | MalformedJwtException e) {
            throw new IllegalArgumentException("A");
        } catch (final SignatureException e) {
            throw new IllegalArgumentException("B");
        } catch (final ExpiredJwtException e) {
            throw new IllegalArgumentException("C");
        }
    }
    
    private void validateExpiredToken(final Jws<Claims> claims) {
        if (claims.getBody().getExpiration().before(new Date())) {
            throw new IllegalArgumentException();
        }
    }
}
