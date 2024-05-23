package com.example.net0backend.controller;

import com.example.net0backend.dto.response.TokenRefreshResponse;
import com.example.net0backend.dto.request.TokenRefreshRequest;
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
    public ResponseEntity<SignInResponse> login(@RequestBody @Valid SignInRequest signInRequest) { // 카카오 계정 받음
        return loginService.signIn(signInRequest);
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody @Valid TokenRefreshRequest tokenRefreshRequest) {
        return loginService.validRefreshToken(tokenRefreshRequest);
    }
}
