package com.example.groom.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpException;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ACCESS_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, "액세스 토큰이 필요한 요청입니다."),
    REFRESH_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, "리프레쉬 토큰이 필요한 요청입니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "리프레쉬 토큰이 만료되었습니다."),
    TOKEN_VALIDATE_FAIL(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰 입니다."),
    REFRESH_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "리프레쉬토큰이 서버에 존재하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "로그인 정보를 찾을 수 없습니다."),
    ORGANIZATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지않는 조직 정보입니다."),
    JOIN_REQUIRED(HttpStatus.NOT_FOUND, "회원가입이 필요합니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
    GIFTICON_NOT_FOUND(HttpStatus.NOT_FOUND, "기프티콘을 찾을 수 없습니다."),
    KAKAO_INFO_GET_REQUEST_FAILD(HttpStatus.NOT_ACCEPTABLE, "카카오 정보 가져오기에 실패했습니다."),
    // Todo error
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "할 일을 찾을 수 없습니다."),

    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "모임을 찾을 수 없습니다."),

    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."),
    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드를 실패했습니다.")
    ;

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpException e) {
        this.message = e.getMessage();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
