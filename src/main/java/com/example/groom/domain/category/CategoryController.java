package com.example.groom.domain.category;


import com.example.groom.domain.category.dto.CategoryDto;
import com.example.groom.domain.category.dto.CategoryPostDto;
import com.example.groom.domain.category.dto.CategorySearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public Slice<CategoryDto> search(Pageable pageable, CategorySearchCondition condition){
        return this.categoryService.search(pageable, condition);
    }

    @PostMapping
    public CategoryDto save(@RequestBody CategoryPostDto categoryPostDto){
        return this.categoryService.save(categoryPostDto);
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id){
        return this.categoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id){
        return this.categoryService.deleteById(id);
    }
}
