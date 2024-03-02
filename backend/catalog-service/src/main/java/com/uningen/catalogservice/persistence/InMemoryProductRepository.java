package com.uningen.catalogservice.persistence;

import com.uningen.catalogservice.domain.Product;
import com.uningen.catalogservice.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private static final Map<Long, Product> products =
            new ConcurrentHashMap<>();
    @Override
    public Iterable<Product> findAll() {
        return products.values();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return existsById(id) ? Optional.of(products.get(id)) : Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return products.get(id) != null;
    }

    @Override
    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }
}
