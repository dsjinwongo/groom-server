package com.example.groom.domain.auth.userInfo.dto;


import com.example.groom.entity.domain.auth.KakaoInfo;
import com.example.groom.entity.domain.auth.UserInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoDto {
    private Long id;
    private KakaoInfo kakao;
    private String username;

    protected UserInfoDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.kakao = userInfo.getKakao();
        this.username = userInfo.getUser().getUsername();
    }

    public static UserInfoDto of(UserInfo receiver) {
        return new UserInfoDto(receiver);
    }
}
