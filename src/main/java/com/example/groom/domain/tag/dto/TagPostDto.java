package com.example.groom.domain.tag.dto;

import lombok.Data;

@Data
public class TagPostDto {

    private String name;

    public TagPostDto(String name) {
        this.name = name;
    }
}
