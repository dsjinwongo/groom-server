package com.example.groom.common.auth;

import com.example.groom.common.auth.jwt.AuthenticationToken;
import com.example.groom.common.auth.jwt.JwtAuthentication;
import com.example.groom.common.auth.jwt.JwtAuthenticationTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtAuthenticateFilter extends OncePerRequestFilter {
    @Autowired
    private JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;

    private JwtAuthentication getAuthentication(String token) {
        Jws<Claims> claimsJws = jwtAuthenticationTokenProvider.validateAccessToken(token);
        return new JwtAuthentication(Long.valueOf(claimsJws.getBody().getSubject()));
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper(response);
        AuthenticationToken token = jwtAuthenticationTokenProvider.getTokenFromHeader(request);
        if (token.getAccessToken() == null) {

            filterChain.doFilter(wrappingRequest, wrappingResponse);
            wrappingResponse.copyBodyToResponse();
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token.getAccessToken()));
        filterChain.doFilter(wrappingRequest, wrappingResponse);
        wrappingResponse.copyBodyToResponse();


    }
}
