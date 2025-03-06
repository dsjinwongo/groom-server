package com.example.groom.domain.tag.dto;

import lombok.Data;


@Data
public class TagGetDto {
    private Long id;
    private String name;

    public TagGetDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
