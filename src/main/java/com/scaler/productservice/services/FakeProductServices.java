package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

import java.util.List;

public interface FakeProductServices {
    Product fetchSingleProduct(Long id);

    List<Product> fetchAllProducts();
}
