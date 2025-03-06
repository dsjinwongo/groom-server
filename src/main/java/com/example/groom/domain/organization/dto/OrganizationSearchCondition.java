package com.example.groom.domain.organization.dto;

import com.querydsl.core.BooleanBuilder;
import lombok.Getter;

import java.util.List;

import static com.example.groom.entity.domain.organization.QOrganization.organization;

@Getter
public class OrganizationSearchCondition {
    private Long id;
    private String name;
    private String description;
    private List<Long> categoryIds;
    private List<Long> parentCategoryIds;

    private BooleanBuilder idEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(id != null)builder.and(organization.id.eq(id));
        return builder;
    }

    private BooleanBuilder nameContains(){
        BooleanBuilder builder = new BooleanBuilder();
        if(name != null && !name.isBlank())builder.and(organization.name.contains(name));
        return builder;
    }

    private BooleanBuilder descriptionContains(){
        BooleanBuilder builder = new BooleanBuilder();
        if(description != null && !description.isBlank())builder.and(organization.description.contains(description));
        return builder;
    }

    private BooleanBuilder categoryIdIn(){
        BooleanBuilder builder = new BooleanBuilder();
        if(categoryIds != null && categoryIds.size() > 0)builder.and(organization.category.id.in(categoryIds));
        return builder;
    }

    private BooleanBuilder parentIdIn(){
        BooleanBuilder builder = new BooleanBuilder();
        if(parentCategoryIds != null && categoryIds.size() > 0)builder.and(organization.category.parent.id.in(parentCategoryIds));
        return builder;
    }

    public BooleanBuilder getAllCondition(){
        return new BooleanBuilder().and(idEq()).and(nameContains()).and(descriptionContains()).and(categoryIdIn())
                .and(parentIdIn());
    }
}
