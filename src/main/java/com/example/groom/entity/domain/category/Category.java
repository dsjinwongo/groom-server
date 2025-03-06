package com.example.groom.entity.domain.category;


import com.example.groom.domain.category.dto.CategoryPostDto;
import com.example.groom.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorColumn
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Category extends BaseEntity {
    @Column
    private String name;

    @Column
    private Long depth;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category parent;

    protected Category(Long categoryId) {
        super(categoryId);
    }

    static public Category of(CategoryPostDto categoryDto){
        return new Category(categoryDto);
    }

    protected Category(CategoryPostDto categoryPostDto){
        this.name = categoryPostDto.getName();
        this.depth = categoryPostDto.getDepth();
        this.parent = Category.of(categoryPostDto.getParentId());
    }

    private static Category of(Long id) {
        return new Category(id);
    }
}
