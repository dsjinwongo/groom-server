package com.example.groom.entity.domain.auth;


import com.example.groom.entity.common.BaseEntity;
import com.example.groom.entity.domain.room.RoomParticipants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.querydsl.core.annotations.QueryInit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {


    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @JoinColumn
    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @QueryInit({"kakaoAccount.profile"})
    private KakaoInfo kakao;

    @OneToMany(mappedBy = "roomParticipant", cascade = CascadeType.ALL)
    private List<RoomParticipants> roomParticipants = new ArrayList<>();

    protected UserInfo(KakaoInfo kakao, User user) {
        this.user = user;
        this.kakao = kakao;
    }

    public UserInfo(Long id) {
        super(id);
    }

    public static UserInfo of(Long id) {
        return new UserInfo(id);
    }

    public static UserInfo of(KakaoInfo kakao, User user) {
        return new UserInfo(kakao, user);
    }
}
