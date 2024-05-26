package com.example.net0backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String kakaoAccount;
    @NotBlank(message = "refreshToken 은 필수 값 입니다.")
    private String refreshToken;
}
