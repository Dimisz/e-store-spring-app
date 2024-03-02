package com.uningen.catalogservice.domain;

import java.util.Optional;

public interface ProductRepository {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    boolean existsById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
