package com.example.net0backend.dto.response;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class SignInResponse {
    private String accessToken;
    private String refreshToken;
    private int expirationTime;
    private String tokenType;

    private SignInResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expirationTime = 3600;
        this.tokenType = "Bearer";
    }

    public static ResponseEntity<SignInResponse> success(String accessToken, String refreshToken) {
        return ResponseEntity.ok(new SignInResponse(accessToken, refreshToken));
    }

}
