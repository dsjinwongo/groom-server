package com.example.groom.domain.product.dto;

import lombok.Data;

@Data
public class ProductPostDto {

    private Long price;

    private String name;

    private String thumbnailImage;

    private Long companyId;
}
