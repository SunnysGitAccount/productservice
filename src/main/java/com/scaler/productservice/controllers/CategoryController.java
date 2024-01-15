package com.scaler.productservice.controllers;

import com.scaler.productservice.exceptions.NoCategoryFoundForGivenId;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.services.FakeCategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final FakeCategoryServices fakeCategoryServices;
    @GetMapping
    public List<Category> getAllCategories() {
        return fakeCategoryServices.fetchAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) throws NoCategoryFoundForGivenId {
        return fakeCategoryServices.fetchCategoryById(id);
    }

    @PatchMapping("/{id}")
    public Category patchCategoryById(@PathVariable("id") Long id,
                                      @RequestBody Category category) throws NoCategoryFoundForGivenId {
        return fakeCategoryServices.modifyCategoryForGivenId(id, category);
    }

    @PutMapping("/{id}")
    public Category putCategoryById(@PathVariable("id") Long id,
                                    @RequestBody Category category) {
        return fakeCategoryServices.replaceCategoryForGivenId(id, category);
    }
}
