package com.scaler.productservice.services.impl;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.services.FakeCategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FakeCategoryServicesImpl implements FakeCategoryServices {
    private final RestTemplate restTemplate;
    public static final String FAKE_STORE_CATEGORIES_URL = "https://fakestoreapi.com/products/categories";

    @Override
    public List<Category> fetchAllCategories() {
        String[] categoriesList = restTemplate.getForObject(FAKE_STORE_CATEGORIES_URL,
                String[].class);

        AtomicReference<Long> id = new AtomicReference<>(1L);
        if (categoriesList == null)
            return new ArrayList<>();

//        BigDecimal test = BigDecimal.ONE.divide(BigDecimal.ZERO, RoundingMode.HALF_DOWN);
        return Arrays.stream(categoriesList)
                .map(category -> {
                    Category obj = new Category();
                    obj.setId(id.get());
                    obj.setName(category);
                    id.getAndSet(id.get() + 1);
                    return obj;
                }).collect(Collectors.toList());
    }

    @Override
    public Category fetchCategoryById(Long id) {
        return null;
    }

    @Override
    public Category modifyCategoryForGivenId(Long id, Category category) {
        return null;
    }

    @Override
    public Category replaceCategoryForGivenId(Long id, Category category) {
        return null;
    }
}
