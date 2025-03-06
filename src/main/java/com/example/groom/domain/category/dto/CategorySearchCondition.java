package com.example.groom.domain.category.dto;

import com.querydsl.core.BooleanBuilder;
import lombok.Data;

import static com.example.groom.entity.domain.category.QCategory.category;

@Data
public class CategorySearchCondition {
    private Long depthLoe;
    private Long depthGoe;
    private Long parentId;
    private String name;

    private BooleanBuilder nameContains(){
        BooleanBuilder builder = new BooleanBuilder();
        if(name != null && !name.isBlank())builder.and(category.name.contains(name));
        return builder;
    }

    private BooleanBuilder depthBetween(){
        BooleanBuilder builder = new BooleanBuilder();
        if(depthGoe != null)builder.and(category.depth.goe(depthGoe));
        if(depthLoe != null)builder.and(category.depth.loe(depthLoe));
        return builder;
    }

    private BooleanBuilder parentIdEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(parentId != null)builder.and(category.parent.id.eq(parentId));
        return builder;
    }

    public BooleanBuilder getAllCondition(){
        return new BooleanBuilder().and(nameContains()).and(depthBetween()).and(parentIdEq());
    }
}
