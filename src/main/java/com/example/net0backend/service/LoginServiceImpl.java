package com.example.net0backend.service;

import com.example.net0backend.auth.JWTProvider;
import com.example.net0backend.auth.JWTUserInfo;
import com.example.net0backend.dto.request.SignInRequest;
import com.example.net0backend.dto.response.SignInResponse;
import com.example.net0backend.entity.Users;
import com.example.net0backend.repository.UsersRepository;
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

    private final UsersRepository usersRepository;
    private final JWTProvider jwtProvider;

    @Override
    @Transactional
    public ResponseEntity<? super SignInResponse> signIn(SignInRequest signInRequest) {
        Users user = usersRepository.findByEmail(signInRequest.getKakaoAccount())
                .orElseThrow(() -> new RuntimeException("존재 하지 않는 유저입니다."));
        String accessToken = jwtProvider.createAccessToken(JWTUserInfo.from(user));
        String refreshToken = jwtProvider.createRefreshToken(JWTUserInfo.from(user));
        user.updateRefreshToken(refreshToken);
        user.updateLastLogin();
        return SignInResponse.success(accessToken, refreshToken);
    }
}
