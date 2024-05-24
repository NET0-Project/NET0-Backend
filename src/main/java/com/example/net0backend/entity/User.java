package com.example.net0backend.entity;

import com.example.net0backend.dto.request.SignInRequest;
import com.example.net0backend.entity.metadata.BaseTimeEntity;
import com.example.net0backend.enums.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String name;

    private String kakaoEmail;

    private Long kakaoPK;

    private String kakaoProfile;

    private String password;

    private String refreshToken;

    private LocalDateTime lastLoginAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public User(String name, String kakaoEmail, Long kakaoPK, String kakaoProfile, String password, String refreshToken, Shop shop, UserType userType) {
        this.name = name;
        this.kakaoEmail = kakaoEmail;
        this.kakaoPK = kakaoPK;
        this.kakaoProfile = kakaoProfile;
        this.password = password;
        this.refreshToken = refreshToken;
        this.shop = shop;
        this.userType = userType;
    }

    //== 비즈니스 로직 ==//
    public void updateLastLogin() {
        this.lastLoginAt = LocalDateTime.now();
    }
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static User signupUser(SignInRequest signInRequest) {
        return User.builder()
                .kakaoEmail(signInRequest.getKakaoAccount())
                .kakaoPK(signInRequest.getKakaoPK())
                .kakaoProfile(signInRequest.getProfile())
                .userType(UserType.ROLE_USER)
                .build();
    }


}
