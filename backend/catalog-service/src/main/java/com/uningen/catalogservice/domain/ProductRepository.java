package com.uningen.catalogservice.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findById(Long id);
    boolean existsById(Long id);

    @Transactional
    void deleteById(Long id);
}
