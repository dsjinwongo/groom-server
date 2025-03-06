package com.example.groom.domain.auth;


import com.example.groom.common.auth.jwt.AuthenticationToken;
import com.example.groom.common.auth.jwt.AuthenticationTokenProvider;
import com.example.groom.common.auth.jwt.JwtAuthentication;
import com.example.groom.domain.auth.refreshToken.RefreshTokenService;
import com.example.groom.domain.auth.userInfo.UserInfoService;
import com.example.groom.domain.auth.userInfo.dto.UserInfoRoomDto;
import com.example.groom.entity.domain.auth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenService refreshTokenService;
    private final AuthenticationTokenProvider authenticationTokenProvider;
    private final KakaoService kakaoService;

    private final UserInfoService userInfoService;

    public AuthenticationToken tokenRefresh(String refreshToken) {
        return this.refreshTokenService.reissue(refreshToken);
    }


    public AuthenticationToken login(String kakaoCode) {
        UserInfo userInfo = this.kakaoService.getUserInfoByKakaoAccessToken(kakaoCode);
        AuthenticationToken issuedToken = this.authenticationTokenProvider.issue(userInfo.getId());
        this.refreshTokenService.assignRefreshToken(issuedToken.getRefreshToken(), userInfo);
        return issuedToken;
    }

    public UserInfoRoomDto getMe(JwtAuthentication authentication) {
        return UserInfoRoomDto.of(this.userInfoService.getUserInfo(authentication.getPrincipal()));
    }

}
