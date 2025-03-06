package com.example.groom.entity.common;


import com.example.groom.entity.domain.auth.UserInfo;
import com.querydsl.core.annotations.QueryInit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OwnEntity extends BaseEntity {
    @CreatedBy
    @JoinColumn
    @ManyToOne
    @QueryInit("kakao.kakaoAccount.profile")
    private UserInfo owner;

    protected OwnEntity(Long id) {
        super(id);
    }
}
