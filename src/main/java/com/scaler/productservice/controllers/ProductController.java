package com.scaler.productservice.controllers;

import com.scaler.productservice.exceptions.NoProductFoundForGivenId;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeProductServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final FakeProductServices fakeProductServices;

    public ProductController(FakeProductServices fakeProductServices) {
        this.fakeProductServices = fakeProductServices;
    }

    @GetMapping() // localhost:8080/products
    public List<Product> getAllProducts() {
        return fakeProductServices.fetchAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws NoProductFoundForGivenId {
        return fakeProductServices.fetchSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@Validated @RequestBody Product product) {
        return fakeProductServices.addProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws NoProductFoundForGivenId {
        return fakeProductServices.patchProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws NoProductFoundForGivenId {
        return fakeProductServices.putProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        fakeProductServices.deleteProduct(id);
    }


}
