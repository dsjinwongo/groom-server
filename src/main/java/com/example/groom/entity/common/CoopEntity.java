package com.example.groom.entity.common;


import com.example.groom.entity.domain.auth.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CoopEntity extends OwnEntity{
    @LastModifiedBy
    @JoinColumn
    @ManyToOne
    private UserInfo modifier;

    protected CoopEntity(Long roomSlotId) {
        super(roomSlotId);
    }


}
