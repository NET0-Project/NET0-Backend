package com.example.net0backend.auth;

import com.example.net0backend.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTUserInfo {
    private Long userId;
    private String email;
    private String role; // 일반 유저, 사장님

    public static JWTUserInfo from(User user) {
        return JWTUserInfo.builder()
                .userId(user.getId())
                .email(user.getKakaoEmail())
                .role(user.getUserType().toString())
                .build();
    }
}
