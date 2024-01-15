package com.scaler.productservice.repository;

import com.scaler.productservice.models.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @Override
    @NonNull
    List<Product> findAll();

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    Product save(@NonNull Product product);
}
