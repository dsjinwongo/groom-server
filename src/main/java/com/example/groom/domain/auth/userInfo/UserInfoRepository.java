package com.example.groom.domain.auth.userInfo;

import com.example.groom.entity.domain.auth.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByKakaoId(Long kakaoId);
}
