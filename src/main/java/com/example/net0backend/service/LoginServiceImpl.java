package com.example.net0backend.service;

import com.example.net0backend.auth.JWTProvider;
import com.example.net0backend.auth.JWTUserInfo;
import com.example.net0backend.dto.request.SignInRequest;
import com.example.net0backend.dto.request.TokenRefreshRequest;
import com.example.net0backend.dto.response.SignInResponse;
import com.example.net0backend.dto.response.TokenRefreshResponse;
import com.example.net0backend.entity.User;
import com.example.net0backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    @Override
    @Transactional
    public ResponseEntity<SignInResponse> signIn(SignInRequest signInRequest) {
        User user = userRepository.findByKakaoEmail(signInRequest.getKakaoAccount())
                .orElseGet(() ->  signUp(signInRequest));
        String accessToken = jwtProvider.createAccessToken(JWTUserInfo.from(user));
        String refreshToken = jwtProvider.createRefreshToken(JWTUserInfo.from(user));
        user.updateRefreshToken(refreshToken);
        user.updateLastLogin();
        return SignInResponse.success(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public User signUp(SignInRequest signInRequest) {
        User user = User.signupUser(signInRequest);
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<TokenRefreshResponse> validRefreshToken(TokenRefreshRequest tokenRefreshRequest) {
        User user = userRepository.findByKakaoEmail(tokenRefreshRequest.getKakaoAccount())
                .orElseThrow(() -> new RuntimeException("존재 하지 않는 유저입니다."));
        if (!user.getRefreshToken().equals(tokenRefreshRequest.getRefreshToken())) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
        }
        return ResponseEntity.ok(TokenRefreshResponse.of(jwtProvider.createAccessToken(JWTUserInfo.from(user))));
    }
}
