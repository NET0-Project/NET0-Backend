package com.example.net0backend.error.exception.NotFoundException;


import com.example.net0backend.error.ErrorCode;

public class StoreNotFoundException extends NotFoundException {
    public StoreNotFoundException(ErrorCode errorCode) {
        super(ErrorCode.STORE_NOT_FOUND);
    }
}
