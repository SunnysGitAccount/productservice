package com.scaler.productservice.controllers;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeProductServices;
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
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return fakeProductServices.fetchSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
        Product p = new Product();
        p.setTitle(product.getTitle());
        return p;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product temp = fakeProductServices.fetchSingleProduct(id);
        if (product.getCategory().getName() != null) temp.setCategory(product.getCategory());
        return temp;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product temp = fakeProductServices.fetchSingleProduct(id);
        temp.setTitle(product.getTitle());
        return temp;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        System.out.println("Product deleted with id: " + id);
    }


}
