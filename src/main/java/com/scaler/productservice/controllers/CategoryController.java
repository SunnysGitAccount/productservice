package com.scaler.productservice.controllers;

import com.scaler.productservice.services.FakeCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    private final FakeCategoryService fakeCategoryService;

    public CategoryController(FakeCategoryService fakeCategoryService) {
        this.fakeCategoryService = fakeCategoryService;
    }

    @GetMapping
    public String[] getCategories() {
        return fakeCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public String getCategory(@PathVariable("id") Long id) {
        return fakeCategoryService.getCategory(id);
    }
}
