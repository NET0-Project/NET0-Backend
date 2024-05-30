package com.example.net0backend.dto.response;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
public class SignInResponse {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime tokenCreatedAt;
    private int expirationTime;
    private String tokenType;

    private SignInResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenCreatedAt = LocalDateTime.now();
        this.expirationTime = 3600;
        this.tokenType = "Bearer";
    }

    public static ResponseEntity<SignInResponse> success(String accessToken, String refreshToken) {
        return ResponseEntity.ok(new SignInResponse(accessToken, refreshToken));
    }

}
