package com.example.groom.domain.product.dto;


import com.example.groom.domain.organization.dto.OrganizationDto;
import com.example.groom.entity.domain.organization.Organization;
import com.example.groom.entity.domain.product.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {
    private Long id;
    private String thumbnailImage;
    private OrganizationDto organization;
    private Long price;
    private String name;

    public ProductDto(Product product) {
        this.price = product.getPrice();
        this.id = product.getId();
        this.thumbnailImage = product.getThumbnailImage();
        this.name = product.getName();
        this.organization = OrganizationDto.of(product.getOrganization());
    }

    public ProductDto(Long id, String thumbnailImage, Organization organization, Long price, String name) {
        this.id = id;
        this.thumbnailImage = thumbnailImage;
        this.organization = OrganizationDto.of(organization);
        this.price = price;
        this.name = name;
    }

    static public ProductDto of(Product product){
        return new ProductDto(product);
    }
}
