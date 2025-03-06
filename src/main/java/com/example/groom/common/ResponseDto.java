package com.example.groom.common;


import com.example.groom.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Nullable;


@AllArgsConstructor
@Builder
@Getter
public class ResponseDto<T> {
    private final Boolean success;
    private final T data;
    private final ExceptionDto error;



    public ResponseDto(@Nullable T data){
        this.success = true;
        this.data = data;
        this.error = null;
    }


    public static ResponseEntity<Object> toResponseEntity(CustomException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ResponseDto.builder().data(null).error(new ExceptionDto(e)).success(false));
    }

    public static ResponseEntity<Object> toResponseEntity(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.builder().data(null).error(new ExceptionDto(e)).success(false));
    }
}
