package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

import java.util.List;

public interface FakeProductServices {
    Product fetchSingleProduct(Long id);

    List<Product> fetchAllProducts();

    Product addProduct(Product product);

    Product patchProduct(Long id, Product product);

    Product putProduct(Long id, Product product);

    void deleteProduct(Long id);
}
