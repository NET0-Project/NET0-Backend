package com.example.net0backend.error.exception;

import com.example.net0backend.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class APIException extends RuntimeException{
    private ErrorCode errorCode;
}
