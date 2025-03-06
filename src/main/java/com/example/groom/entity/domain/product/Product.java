package com.example.groom.entity.domain.product;


import com.example.groom.domain.product.dto.ProductPostDto;
import com.example.groom.entity.common.BaseEntity;
import com.example.groom.entity.domain.organization.Organization;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Column
    private String name;

    @Column
    private Long price;

    @Column
    private String thumbnailImage;

    @JoinColumn
    @ManyToOne
    private Organization organization;

    protected Product(ProductPostDto productPostDto) {
        this.price = productPostDto.getPrice();
        this.name = productPostDto.getName();
        this.organization = Organization.of(productPostDto.getCompanyId());
        this.thumbnailImage = productPostDto.getThumbnailImage();
    }

    static public Product of(Long id){
        return new Product(id);
    }

    static public Product of(ProductPostDto productPostDto){
        return new Product(productPostDto);
    }
    private Product(Long gifticonId) {
        super(gifticonId);
    }
}
