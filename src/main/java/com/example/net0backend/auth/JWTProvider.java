package com.example.net0backend.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    private final long EXPIRED_TIME = 2;
    public String createToken(JWTUserInfo userInfo) {
        Date expiredDate = Date.from(Instant.now().plus(EXPIRED_TIME, ChronoUnit.HOURS));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(userInfo.getEmail()) // 사용자 식별자
                .claim("userId", userInfo.getUserId())
                .claim("role", userInfo.getRole())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    public String getSubject(String token) {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }
}
