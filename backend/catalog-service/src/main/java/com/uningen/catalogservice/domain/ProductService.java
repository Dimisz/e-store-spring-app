package com.uningen.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> viewProductList(){
        return productRepository.findAll();
    }

    public Product viewProductDetails(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product addProductToCatalog(Product product){

        if(product.getId() != null && productRepository.existsById(product.getId())){
            throw new ProductAlreadyExistsException(product.getId());
        }
        return productRepository.save(Product.of(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getPictureUrl(),
                product.getCategory(),
                product.getBrand(),
                product.getQuantityInStock()));
    }

    public void removeProductFromCatalog(Long id){
        productRepository.deleteById(id);
    }

    public Product editProductDetails(Long id, Product product){
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setPictureUrl(product.getPictureUrl());
                    existingProduct.setCategory(product.getCategory());
                    existingProduct.setBrand(product.getBrand());
                    existingProduct.setQuantityInStock(product.getQuantityInStock());
                    return productRepository.save(existingProduct);
                }).orElseGet(() -> addProductToCatalog(product));
    }

}
