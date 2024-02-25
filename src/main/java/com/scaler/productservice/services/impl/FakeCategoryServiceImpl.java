package com.scaler.productservice.services.impl;

import com.scaler.productservice.services.FakeCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeCategoryServiceImpl implements FakeCategoryService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String[] getAllCategories() {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
    }

    @Override
    public String getCategory(Long id) {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories/" + id, String.class);
    }
}
