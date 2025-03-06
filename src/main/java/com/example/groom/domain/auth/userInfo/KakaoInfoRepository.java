package com.example.groom.domain.auth.userInfo;

import com.example.groom.entity.domain.auth.KakaoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoInfoRepository extends JpaRepository<KakaoInfo, Long> {

}
