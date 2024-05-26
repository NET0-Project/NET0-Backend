package com.example.net0backend.auth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TokenStatus {
    VALID("유효한 토큰"), EXPIRED("만료된 토큰"), INVALID("유효 하지 않은 토큰");

    private final String text;
}
