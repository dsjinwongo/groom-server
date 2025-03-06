package com.example.groom.domain.auth.userInfo.dto;

import com.example.groom.entity.domain.auth.UserInfo;
import com.example.groom.entity.domain.todo.Todo;
import lombok.Data;

@Data
public class UserInfoRoomDto {

    private Long id;

    private String nickname;

    private String profileImageUrl;

    protected UserInfoRoomDto(Long id, String nickname, String profileImageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public static UserInfoRoomDto of(UserInfo userInfo) {
        return new UserInfoRoomDto(
                userInfo.getId(),
                userInfo.getKakao().getKakaoAccount().getProfile().getNickname(),
                userInfo.getKakao().getKakaoAccount().getProfile().getProfileImageUrl()
        );
    }

    public static UserInfoRoomDto of(Todo todo) {
        return new UserInfoRoomDto(
                todo.getTodoOwner().getId(),
                todo.getTodoOwner().getKakao().getKakaoAccount().getProfile().getNickname(),
                todo.getTodoOwner().getKakao().getKakaoAccount().getProfile().getProfileImageUrl()
        );
    }

}
