package com.example.groom.domain.category;


import com.example.groom.common.exception.CustomException;
import com.example.groom.domain.category.dto.CategoryDto;
import com.example.groom.domain.category.dto.CategoryPostDto;
import com.example.groom.domain.category.dto.CategorySearchCondition;
import com.example.groom.entity.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.groom.common.exception.ErrorCode.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private Category getCategoryById(Long category_id) {
        Optional<Category> category = this.categoryRepository.findById(category_id);
        if(category.isEmpty())throw new CustomException(CATEGORY_NOT_FOUND);
        return category.get();
    }


    public Slice<CategoryDto> search(Pageable pageable, CategorySearchCondition condition) {
        return this.categoryRepository.search(pageable, condition);
    }

    public CategoryDto save(CategoryPostDto categoryPostDto) {
        Category category = Category.of(categoryPostDto);
        category = this.categoryRepository.save(category);
        return CategoryDto.of(category);
    }


    public CategoryDto findById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new CustomException(CATEGORY_NOT_FOUND));
        return CategoryDto.of(category);

    }

    public Long deleteById(Long id) {
        this.categoryRepository.deleteById(id);
        return id;
    }
}
