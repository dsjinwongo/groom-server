package com.example.groom.entity.domain.tag;


import com.example.groom.domain.tag.dto.TagPostDto;
import com.example.groom.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity {
    @Column
    private String name;

    public static Tag of(TagPostDto tagPostDto){
        return new Tag(tagPostDto.getName());
    }
}
