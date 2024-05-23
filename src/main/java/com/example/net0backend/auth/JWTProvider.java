package com.example.net0backend.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JWTProvider {

    @Value("${jwt.secret_key}")
    private String secretKey;
    @Value("${jwt.access_token_validity_seconds}")
    private long ACCESS_TOKEN_EXP;
    @Value("${jwt.refresh_token_validity_seconds}")
    private long REFRESH_TOKEN_EXP;
    public final String TOKEN_PRIFIX = "Bearer ";

    /**
     * JWT 토큰 생성
     */
    private String createToken(JWTUserInfo userInfo, long expiredTime) {
        Date expiredDate = Date.from(Instant.now().plus(expiredTime, ChronoUnit.HOURS));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(userInfo.getEmail()) // 사용자 식별자
                .claim("userId", userInfo.getUserId())
                .claim("role", userInfo.getRole())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    public String createAccessToken(JWTUserInfo userInfo) {
        return createToken(userInfo, ACCESS_TOKEN_EXP);
    }

    public String createRefreshToken(JWTUserInfo userInfo) {
        return createToken(userInfo, REFRESH_TOKEN_EXP);
    }

    /**
     * 토큰 유효성 검사
     */
    public TokenStatus validateToken(String authorizationHeader) {
        String token = parseBearerToken(authorizationHeader);
        try{
            Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return TokenStatus.VALID;
        } catch (ExpiredJwtException ex) {
            log.info("token is Expired: {}", ex.getMessage());
            return TokenStatus.EXPIRED;
        } catch (Exception ex) {
            log.info("token Exception: {}", ex.getMessage());
            return TokenStatus.INVALID;
        }
    }

    /**
     * 토큰 유효성 검사 후 사용자 이메일 반환
     */
    public String validateTokenReturnEmail(String authorizationHeader) {
        String token = parseBearerToken(authorizationHeader);
        try {
            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException ex) {
            log.info("token is Expired: {}", ex.getMessage());
            // 토큰 삭제 로직 추가
            throw new RuntimeException("Token is Expired");
        } catch (Exception ex) {
            log.info("token Exception: {}", ex.getMessage());
            // 토큰 삭제 로직 추가
            throw new RuntimeException("Token is Not Valid");
        }
    }

    /**
     * 헤더에서 토큰 추출
     */
    private String parseBearerToken(String authorizationHeader) {
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(TOKEN_PRIFIX)) {
            return authorizationHeader.substring(TOKEN_PRIFIX.length());
        }
        return authorizationHeader;
    }
}
