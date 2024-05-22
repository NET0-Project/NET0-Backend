package com.example.net0backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    ROLE_ADMIN("admin"), ROLE_SELLER("seller"), ROLE_USER("user");

    private final String type;
}
