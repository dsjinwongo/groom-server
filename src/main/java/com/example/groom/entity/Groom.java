package com.example.groom.entity;


import com.example.groom.entity.domain.category.Category;
import com.example.groom.entity.common.OwnEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Groom extends OwnEntity {

    @Column
    private String name;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
