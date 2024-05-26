package com.example.net0backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreStatus {
    FOOD("남아요"), NOFOOD("없어요"), CLOSE("닫았어요");

    private final String status;
}
