package com.example.groom.domain.category;

import com.example.groom.domain.category.dto.CategoryDto;
import com.example.groom.domain.category.dto.CategorySearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepositoryCustom {

    @Transactional
    Slice<CategoryDto> search(Pageable pageable, CategorySearchCondition condition);
}
