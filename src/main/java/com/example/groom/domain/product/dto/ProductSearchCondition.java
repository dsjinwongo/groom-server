package com.example.groom.domain.product.dto;

import com.querydsl.core.BooleanBuilder;
import lombok.Data;

import static com.example.groom.entity.domain.product.QProduct.product;


@Data
public class ProductSearchCondition {
    private String name;
    private Long priceGoe;
    private Long priceLoe;
    private Long companyId;
    private String companyName;
    private Long id;

    private BooleanBuilder nameContains(){
        BooleanBuilder builder = new BooleanBuilder();
        if(name != null && !name.isBlank())builder.and(product.name.contains(name));
        return builder;
    }

    private BooleanBuilder priceBetween(){
        BooleanBuilder builder = new BooleanBuilder();
        if(priceGoe != null)builder.and(product.price.goe(priceGoe));
        if(priceLoe != null)builder.and(product.price.loe(priceLoe));
        return builder;
    }

    private BooleanBuilder companyNameContains(){
        BooleanBuilder builder = new BooleanBuilder();
        if(name != null)builder.and(product.organization.name.contains(companyName));
        return builder;
    }

    private BooleanBuilder companyIdEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(companyId != null)builder.and(product.organization.id.eq(companyId));
        return builder;
    }

    public BooleanBuilder getCondition(){
        BooleanBuilder builder = new BooleanBuilder().and(nameContains()).and(priceBetween()).and(companyNameContains()).and(companyIdEq());
        return builder;
    }
}
