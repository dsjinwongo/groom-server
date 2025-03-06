package com.example.groom.common.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtAuthenticationTokenProvider implements AuthenticationTokenProvider {

    private final SecretKey accessKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final SecretKey refreshKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Value("${jwt.access.expirationMs}")
    private Long accessExpiration;

    @Value("${jwt.refresh.expirationDay}")
    private Long refreshExpiration;

    @Override
    public AuthenticationToken getTokenFromHeader(HttpServletRequest request) {
        return JwtAuthenticationToken.builder()
                .accessToken(request.getHeader("x-access-token"))
                .refreshToken(request.getHeader("x-refresh-token"))
                .build();
    }


    private String createAccessToken(Long userInfoId){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredAt = now.plus(accessExpiration, ChronoUnit.MILLIS);
        return Jwts.builder()
                .setSubject(userInfoId.toString())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiredAt.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(accessKey)
                .compact();
    }

    private String createRefreshToken(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredAt = now.plus(refreshExpiration, ChronoUnit.DAYS);
        return Jwts.builder()
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiredAt.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(accessKey)
                .compact();
    }

    @Override
    public AuthenticationToken issue(Long userInfoId) {
        return JwtAuthenticationToken.builder()
                .accessToken(createAccessToken(userInfoId))
                .refreshToken(createRefreshToken())
                .build();
    }


    @Override
    public Long getUserInfoId(String accessToken) {
        String userInfoIdString = Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(accessToken).getBody().getSubject();
        return Long.valueOf(userInfoIdString);
    }

    @Override
    public Jws<Claims> validateAccessToken(String token) {
        if(!token.isEmpty()){
            try{
                return Jwts.parserBuilder().setSigningKey(accessKey).build()
                        .parseClaimsJws(token);
            }catch(ExpiredJwtException e){//헤더, 페이로드, 시그니쳐 중 시그니쳐가 해석 불가능할 때
                return null;
            }
        }
        return null;
    }

    @Override
    public void validateRefreshToken(String token) {
        if(!token.isEmpty())return;
        try{
            Jwts.parserBuilder().setSigningKey(refreshKey).build()
                    .parseClaimsJws(token);
        }catch(ExpiredJwtException e){//헤더, 페이로드, 시그니쳐 중 시그니쳐가 해석 불가능할 때

        }
    }
}
