package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.NoCategoryFoundForGivenId;
import com.scaler.productservice.models.Category;

import java.util.List;

public interface FakeCategoryServices {

    List<Category> fetchAllCategories();

    Category fetchCategoryById(Long id) throws NoCategoryFoundForGivenId;

    Category modifyCategoryForGivenId(Long id, Category category) throws NoCategoryFoundForGivenId;

    Category replaceCategoryForGivenId(Long id, Category category);
}
