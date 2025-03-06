package com.example.groom.domain.auth.refreshToken;


import com.example.groom.common.auth.jwt.AuthenticationToken;
import com.example.groom.common.auth.jwt.AuthenticationTokenProvider;
import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import com.example.groom.entity.domain.auth.RefreshToken;
import com.example.groom.entity.domain.auth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refresh.expirationDay}")
    private Long expirationDay;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationTokenProvider authenticationTokenProvider;

    private boolean isExpired(RefreshToken existsRefreshToken) {
        if (!existsRefreshToken.getCreatedAt().plusDays(expirationDay).isBefore(now())) return false;
        this.refreshTokenRepository.delete(existsRefreshToken);
        return true;
    }
    public UserInfo getUserInfoFromToken(String refreshToken){
        RefreshToken refreshTokenFromDB = this.refreshTokenRepository.getRefreshTokenFromDB(refreshToken);
        if(isExpired(refreshTokenFromDB))throw new CustomException(ErrorCode.REFRESH_TOKEN_INVALID);
        return refreshTokenFromDB.getUserInfo();
    }


    public void assignRefreshToken(String refreshToken, UserInfo userInfo){
        this.refreshTokenRepository.assignRefreshToken(refreshToken,userInfo);
    }

    public AuthenticationToken reissue(String refreshToken){
        RefreshToken token = this.refreshTokenRepository.getRefreshTokenFromDB(refreshToken);
        UserInfo userInfo = token.getUserInfo();
        AuthenticationToken issue = this.authenticationTokenProvider.issue(userInfo.getId());
        this.refreshTokenRepository.delete(token);
        this.refreshTokenRepository.save(new RefreshToken(issue.getRefreshToken(), userInfo));
        return issue;
    }
}
