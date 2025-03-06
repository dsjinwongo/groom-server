package com.example.groom.entity;

import com.example.groom.entity.common.OwnEntity;
import com.example.groom.entity.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExcuseUser extends OwnEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Excuse excuse;
}
