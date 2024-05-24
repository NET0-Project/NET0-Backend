package com.example.net0backend.service;

import com.example.net0backend.dto.request.SignInRequest;
import com.example.net0backend.dto.request.TokenRefreshRequest;
import com.example.net0backend.dto.response.SignInResponse;
import com.example.net0backend.dto.response.TokenRefreshResponse;
import com.example.net0backend.entity.User;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<SignInResponse> signIn(SignInRequest signInRequest);
    User signUp(SignInRequest signInRequest);
    ResponseEntity<TokenRefreshResponse> validRefreshToken(TokenRefreshRequest tokenRefreshRequest);
}
