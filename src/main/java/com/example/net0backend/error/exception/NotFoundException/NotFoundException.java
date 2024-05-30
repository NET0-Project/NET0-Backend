package com.example.net0backend.error.exception.NotFoundException;

import com.example.net0backend.error.ErrorCode;
import com.example.net0backend.error.exception.APIException;
import lombok.Getter;

@Getter
public class NotFoundException extends APIException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
