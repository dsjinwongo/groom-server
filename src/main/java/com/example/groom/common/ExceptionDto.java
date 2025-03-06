package com.example.groom.common;

import com.example.groom.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@AllArgsConstructor
@Builder
@Getter
public class ExceptionDto {
    private final HttpStatus status;
    private final String code;
    private final String message;

    public ExceptionDto(CustomException e) {
        this.status = e.getErrorCode().getStatus();
        this.code = e.getErrorCode().name();
        this.message = e.getErrorCode().getMessage();
    }

    public ExceptionDto(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string

        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.name();
        this.message = sStackTrace;
    }
}
