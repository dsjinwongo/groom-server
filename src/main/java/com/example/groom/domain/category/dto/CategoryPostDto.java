package com.example.groom.domain.category.dto;


import lombok.Data;

@Data
public class CategoryPostDto {
    private String name;

    private Long depth;

    private Long parentId;
}
