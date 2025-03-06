package com.example.groom.domain.auth.userInfo.dto;

import com.example.groom.entity.domain.auth.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserScheduleDto {

    private Long id;

    private String username;

    private String profileImage;

    public static UserScheduleDto of(UserInfo userInfo) {
        return UserScheduleDto.builder()
                .id(userInfo.getId())
                .username(userInfo.getKakao().getKakaoAccount().getProfile().getNickname())
                .profileImage(userInfo.getKakao().getKakaoAccount().getProfile().getProfileImageUrl())
                .build();
    }
}
