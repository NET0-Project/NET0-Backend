package com.example.net0backend.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JWTProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    private TokenStatus tokenStatus;
    private final long EXPIRED_TIME = 2; // 토큰 만료 시간 2H
    public final String TOKEN_PRIFIX = "Bearer ";

    /**
     * JWT 토큰 생성
     */
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

    /**
     * 토큰 유효성 검사
     */
    public TokenStatus validateToken(String authorizationHeader) {
        String token = getAccessToken(authorizationHeader);
        try{
            Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return TokenStatus.VALID;
        } catch (ExpiredJwtException ex) {
            log.info("token is Expired: {}", ex.getMessage());
            return TokenStatus.EXPIRED;
        }
        catch (Exception ex) {
           log.info("token Exception: {}", ex.getMessage());
            return TokenStatus.INVALID;
        }
    }

    /**
     * 헤더에서 토큰 추출
     */
    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PRIFIX)) {
            return authorizationHeader.substring(TOKEN_PRIFIX.length());
        }
        return authorizationHeader;
    }
}
