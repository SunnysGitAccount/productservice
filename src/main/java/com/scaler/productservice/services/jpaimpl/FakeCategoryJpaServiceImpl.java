package com.scaler.productservice.services.jpaimpl;

import com.scaler.productservice.exceptions.NoCategoryFoundForGivenId;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.repository.CategoryRepository;
import com.scaler.productservice.services.FakeCategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class FakeCategoryJpaServiceImpl implements FakeCategoryServices {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category fetchCategoryById(Long id) throws NoCategoryFoundForGivenId {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoCategoryFoundForGivenId("No category found with given Id!"));
    }

    @Override
    public Category modifyCategoryForGivenId(Long id, Category category) throws NoCategoryFoundForGivenId {
        Category savedCategory = fetchCategoryById(id);

        if (StringUtils.hasText(category.getName())) savedCategory.setName(category.getName());
        return categoryRepository.save(savedCategory);
    }

    @Override
    public Category replaceCategoryForGivenId(Long id, Category category) {
        Category savedCategory;
        try {
            savedCategory = fetchCategoryById(id);
        } catch (NoCategoryFoundForGivenId e) {
//            TODO: Ask if category replace method should
//             create a new category for non-existing category.
            return categoryRepository.save(category);
        }
        savedCategory.setName(category.getName());
        return categoryRepository.save(savedCategory);
    }
}
