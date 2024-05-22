package com.example.net0backend.error.exception;

import com.example.net0backend.error.ErrorCode;

public class NotEnoughStockException extends APIException{
    public NotEnoughStockException(ErrorCode errorCode) {
        super(errorCode);
    }
}

