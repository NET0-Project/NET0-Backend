package com.example.net0backend.controller;

import com.example.net0backend.dto.request.RefreshTokenRequest;
import com.example.net0backend.dto.request.SignInRequest;
import com.example.net0backend.dto.response.SignInResponse;
import com.example.net0backend.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponse> login(@RequestBody @Valid SignInRequest signInRequest) { // 카카오 계정 받음
        return loginService.signIn(signInRequest);
        // 이메일 뒤져서 있는 사용자인지 확인
        //있으면 토큰 발급
        //없으면 그냥 리턴
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<? super SignInResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
    }
}
