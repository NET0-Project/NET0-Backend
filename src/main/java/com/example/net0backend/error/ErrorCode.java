package com.example.net0backend.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //400번대 HTTP 상태 코드
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,"itemAPI","가게 정보가 존재하지 않습니다."),
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST,"itemAPI","재고가 부족합니다."),
    //500번대 HTTP 상태 코드
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "server", "서버에 알 수 없는 오류가 발생했습니다.");

    private HttpStatus status;
    private String code;
    private String message;
}
