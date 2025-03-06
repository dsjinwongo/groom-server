package com.example.groom.domain.category.dto;


import com.example.groom.entity.domain.category.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;

    private Long depth;

    private List<CategoryDto> children;

    protected CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.depth = category.getDepth();
        this.children = category.getChildren().stream().map(CategoryDto::new).toList();
    }

    static public CategoryDto of(Category category){
        return new CategoryDto(category);
    }
}
