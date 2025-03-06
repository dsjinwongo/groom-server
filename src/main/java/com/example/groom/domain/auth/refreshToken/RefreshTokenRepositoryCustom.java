package com.example.groom.domain.auth.refreshToken;

import com.example.groom.entity.domain.auth.RefreshToken;
import com.example.groom.entity.domain.auth.UserInfo;

public interface RefreshTokenRepositoryCustom {

    void assignRefreshToken(String refreshToken, UserInfo userInfo);
    RefreshToken getRefreshTokenFromDB(String refreshToken);

}
