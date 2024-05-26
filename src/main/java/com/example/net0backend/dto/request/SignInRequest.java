package com.example.net0backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank(message = "카카오 계정은 필수값 입니다.")
    private String kakaoAccount;
    private Long kakaoPK;
    private String profile;
}
