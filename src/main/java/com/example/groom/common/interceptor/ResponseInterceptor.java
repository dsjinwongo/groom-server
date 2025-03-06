package com.example.groom.common.interceptor;

import com.example.groom.common.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RequiredArgsConstructor
@Component
public class ResponseInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception ex
    ) throws Exception {
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
            if (cachingResponse.getContentAsByteArray().length != 0) {
                String body = new String(cachingResponse.getContentAsByteArray());
                Object data = objectMapper.readValue(body, Object.class);
                ResponseDto<Object> objectResponseDto = new ResponseDto<>(data);
                String wrappedBody = objectMapper.writeValueAsString(objectResponseDto);
                cachingResponse.resetBuffer();
                cachingResponse.getOutputStream().write(wrappedBody.getBytes(), 0, wrappedBody.getBytes().length);
                log.info("Response Body : {}", wrappedBody);
            }
        }
    }

}
