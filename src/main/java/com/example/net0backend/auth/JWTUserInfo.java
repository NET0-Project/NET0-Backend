package com.example.net0backend.auth;

import lombok.Data;

@Data
public class JWTUserInfo {
    private Long userId;
    private String email;
    private String role; // 일반 유저, 사장님
}
