package com.example.groom.domain.product;


import com.example.groom.domain.product.dto.ProductDto;
import com.example.groom.domain.product.dto.ProductPostDto;
import com.example.groom.domain.product.dto.ProductSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ProductDto postProduct(@RequestBody ProductPostDto productPostDto) {
        return ProductDto.of(this.productService.save(productPostDto));
    }

    @GetMapping
    public List<ProductDto> searchProduct(ProductSearchCondition productSearchCondition) {
        return this.productService.search(productSearchCondition);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id) {
        return this.productService.deleteById(id);
    }
}
