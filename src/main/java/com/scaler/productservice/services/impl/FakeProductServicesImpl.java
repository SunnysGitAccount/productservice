package com.scaler.productservice.services.impl;

import com.scaler.productservice.dtos.FakeStoreDTO;
import com.scaler.productservice.exceptions.NoProductFoundForGivenId;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeProductServices;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductServicesImpl implements FakeProductServices {
    public static final String FAKE_STORE_URL = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate;

    public FakeProductServicesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product fetchSingleProduct(Long id) throws NoProductFoundForGivenId {
        FakeStoreDTO fakeStoreDTO = restTemplate.getForObject(FAKE_STORE_URL + "/" + id,
                FakeStoreDTO.class);

        if (fakeStoreDTO == null) throw new NoProductFoundForGivenId("No product found with given ID!");
        return convertProductDtoToProduct(fakeStoreDTO);
    }

    @Override
    public List<Product> fetchAllProducts() {
        FakeStoreDTO[] fakeStoreDTOList = restTemplate.getForObject(FAKE_STORE_URL,
                FakeStoreDTO[].class);

        return convertAllProductDtoToProduct(fakeStoreDTOList);
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreDTO resp = restTemplate.postForObject(FAKE_STORE_URL,
                convertProductToProductDto(product),
                FakeStoreDTO.class);

        return convertProductDtoToProduct(resp);
    }

    @Override
    public Product patchProduct(Long id, Product product) {
        FakeStoreDTO resp = restTemplate.patchForObject(FAKE_STORE_URL + "/" + id,
                convertProductToProductDto(product),
                FakeStoreDTO.class);

        return convertProductDtoToProduct(resp);
    }

    @Override
    public Product putProduct(Long id, Product product) {
        HttpEntity<FakeStoreDTO> respEntity = restTemplate.exchange(FAKE_STORE_URL + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(convertProductToProductDto(product)),
                FakeStoreDTO.class);
        return convertProductDtoToProduct(respEntity.getBody());
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete(FAKE_STORE_URL + "/" + id);
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

    private FakeStoreDTO convertProductToProductDto(Product product) {
        if (product == null) return new FakeStoreDTO();
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        if (product.getTitle() != null) fakeStoreDTO.setTitle(product.getTitle());
        if (product.getPrice() > 0) fakeStoreDTO.setPrice(product.getPrice());
        if (product.getDescription() != null) fakeStoreDTO.setDescription(product.getDescription());
        if (product.getImageUrl() != null) fakeStoreDTO.setImage(product.getImageUrl());
        if (product.getCategory() != null && product.getCategory().getName() != null) fakeStoreDTO.setCategory(product.getCategory().getName());
        return fakeStoreDTO;
    }
}
