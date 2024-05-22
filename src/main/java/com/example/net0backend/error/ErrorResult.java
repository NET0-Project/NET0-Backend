package com.example.net0backend.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;

    public ErrorResult(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
