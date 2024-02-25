package com.scaler.productservice.services.jpaimpl;

import com.scaler.productservice.exceptions.NoProductFoundForGivenId;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repository.CategoryRepository;
import com.scaler.productservice.repository.ProductRepository;
import com.scaler.productservice.services.FakeProductServices;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class FakeProductJpaServiceImpl implements FakeProductServices {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public FakeProductJpaServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product fetchSingleProduct(Long id) throws NoProductFoundForGivenId {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoProductFoundForGivenId("No product found with given ID!"));
    }

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> savedOptionalCategory = categoryRepository.findByName(product.getCategory().getName());
        savedOptionalCategory.ifPresent(product::setCategory);
        return productRepository.save(product);
    }

    @Override
    public Product patchProduct(Long id, Product product) throws NoProductFoundForGivenId {
        Product savedProduct = fetchSingleProduct(id);

        if (StringUtils.hasText(product.getTitle()))
            savedProduct.setTitle(product.getTitle());
        if (product.getPrice() != null && savedProduct.getPrice().compareTo(product.getPrice()) != 0)
            savedProduct.setPrice(product.getPrice());
        if (StringUtils.hasText(product.getDescription()))
            savedProduct.setDescription(product.getDescription());
        if (StringUtils.hasText(product.getImageUrl()))
            savedProduct.setImageUrl(product.getImageUrl());

        return productRepository.save(savedProduct);
    }

    @Override
    public Product putProduct(Long id, Product product) throws NoProductFoundForGivenId {
        Product savedProduct = fetchSingleProduct(id);
        Category savedCategory;

        savedProduct.setTitle(product.getTitle());
        savedProduct.setPrice(product.getPrice());
        if (categoryRepository.findByName(product.getCategory().getName()).isEmpty())
            savedCategory = categoryRepository.save(product.getCategory());
        else
            savedCategory = categoryRepository.findByName(product.getCategory().getName()).get();
        savedProduct.setCategory(savedCategory);
        savedProduct.setDescription(product.getDescription());
        savedProduct.setImageUrl(product.getImageUrl());

        return productRepository.save(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
