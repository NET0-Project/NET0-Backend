package com.example.net0backend.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRefreshResponse {
    private String accessToken;

    public static TokenRefreshResponse of(String accessToken) {
        return TokenRefreshResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
