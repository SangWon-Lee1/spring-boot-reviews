package com.example.springbootreviews.bank.controller;

import com.example.springbootreviews.bank.exception.CustomerValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Spring MVC의 모든 컨트롤러에서 발생하는 예외 처리
@ControllerAdvice
public class GlobalExceptionHandler {

    // 특정 예외 타입을 처리하는 메소드 정의
    @ExceptionHandler(CustomerValidationException.class)
    public ResponseEntity<String> handleCustomerValidationException(CustomerValidationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // HTTP상태코드(ex 404)
                .body(e.getMessage());
    }
}