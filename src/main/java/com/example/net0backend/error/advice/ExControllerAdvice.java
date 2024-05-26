package com.example.net0backend.error.advice;

import com.example.net0backend.error.ErrorCode;
import com.example.net0backend.error.ErrorResult;
import com.example.net0backend.error.exception.NotFoundException.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity exHandler(Exception e){
        ErrorResult errorResult = new ErrorResult(ErrorCode.SERVER_ERROR);
        return new ResponseEntity(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResult> NotFoundExHandler(NotFoundException e){
        ErrorResult errorResult = new ErrorResult(e.getErrorCode());
        return new ResponseEntity(errorResult, HttpStatus.NOT_FOUND);
    }
}
