package com.example.groom.common.exception;


import com.example.groom.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<Object> handleException(CustomException e){
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ResponseDto.toResponseEntity(e);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleSomeException(Exception e){
        log.error("handleCustomException throw CustomException : {}", e.getMessage());
        return ResponseDto.toResponseEntity(e);
    }

}
