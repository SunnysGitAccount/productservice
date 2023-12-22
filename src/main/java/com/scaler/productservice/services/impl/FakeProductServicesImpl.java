package com.scaler.productservice.services.impl;

import com.scaler.productservice.dtos.FakeStoreDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeProductServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductServicesImpl implements FakeProductServices {
    private final RestTemplate restTemplate;

    public FakeProductServicesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product fetchSingleProduct(Long id) {
        FakeStoreDTO fakeStoreDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreDTO.class);
        return convertProductDtoToProduct(fakeStoreDTO);
    }

    @Override
    public List<Product> fetchAllProducts() {
        FakeStoreDTO[] fakeStoreDTOList = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreDTO[].class);
        return convertAllProductDtoToProduct(fakeStoreDTOList);
    }

    private List<Product> convertAllProductDtoToProduct(FakeStoreDTO[] fakeStoreDTOList) {
        List<Product> products = new ArrayList<>();
        if (fakeStoreDTOList == null) return products;
        for (FakeStoreDTO fakeStoreDTO : fakeStoreDTOList) {
            products.add(convertProductDtoToProduct(fakeStoreDTO));
        }
        return products;
    }

    private Product convertProductDtoToProduct(FakeStoreDTO fakeStoreDTO) {
        if (fakeStoreDTO == null) return new Product();
        Product product = new Product();
        product.setId(fakeStoreDTO.getId());
        product.setTitle(fakeStoreDTO.getTitle());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreDTO.getCategory());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setDescription(fakeStoreDTO.getDescription());
        product.setImageUrl(fakeStoreDTO.getImage());
        return product;
    }
}
