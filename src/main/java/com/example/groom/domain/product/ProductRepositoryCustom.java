package com.example.groom.domain.product;

import com.example.groom.domain.product.dto.ProductDto;
import com.example.groom.domain.product.dto.ProductSearchCondition;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductDto> search(ProductSearchCondition productSearchCondition);
}
