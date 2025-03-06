package com.example.groom.domain.auth.refreshToken;

import com.example.groom.entity.domain.auth.RefreshToken;
import com.example.groom.entity.domain.auth.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>, RefreshTokenRepositoryCustom {
    void deleteByUserInfo(UserInfo userInfo);

    boolean existsByUserInfo(UserInfo userInfo);

    boolean existsByRefreshToken(String refreshToken);
}
