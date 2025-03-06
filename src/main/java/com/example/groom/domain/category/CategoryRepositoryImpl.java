package com.example.groom.domain.category;

import com.example.groom.domain.category.dto.CategoryDto;
import com.example.groom.domain.category.dto.CategorySearchCondition;
import com.example.groom.entity.domain.category.QCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.groom.entity.domain.category.QCategory.category;


public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @Autowired
    private EntityManager em;

    private final JPAQueryFactory query;

    public CategoryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    @Override
    @Transactional
    public Slice<CategoryDto> search(Pageable pageable, CategorySearchCondition condition) {
        List<CategoryDto> categoryDtos = query.select(Projections.constructor(CategoryDto.class, category.name, category.depth, category.children))
                .from(category)
                .join(category.parent, new QCategory("parent"))
                .where(condition.getAllCondition())
                .fetch();

        Long count = query.select(category.count()).from(category).where(condition.getAllCondition())
                .fetchOne();
        return new PageImpl<>(categoryDtos,pageable,  count);
    }
}
