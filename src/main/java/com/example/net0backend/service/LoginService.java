package com.example.net0backend.service;

import com.example.net0backend.dto.request.SignInRequest;
import com.example.net0backend.dto.response.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<? super SignInResponse> signIn(SignInRequest signInRequest);
}
