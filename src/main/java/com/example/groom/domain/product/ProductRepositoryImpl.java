package com.example.groom.domain.product;

import com.example.groom.domain.product.dto.ProductDto;
import com.example.groom.domain.product.dto.ProductSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.groom.entity.domain.product.QProduct.product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory query;
    @Autowired
    private EntityManager em;

    protected ProductRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ProductDto> search(ProductSearchCondition productSearchCondition) {
        List<ProductDto> productDtos = query.select(Projections.constructor(ProductDto.class,
                        product.id,
                        product.thumbnailImage,
                        product.organization,
                        product.price,
                        product.name))
                .from(product)
                .fetch();

        return productDtos;
    }
}
