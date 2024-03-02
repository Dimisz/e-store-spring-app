package com.uningen.catalogservice.domain;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> viewAllProducts(){
        return productRepository.findAll();
    }

    public Product viewProductDetails(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product addProductToCatalog(Product product){
        if(productRepository.existsById(product.getId())){
            throw new ProductAlreadyExistsException(product.getId());
        }
        return productRepository.save(product);
    }

    public void removeProductFromCatalog(Long id){
        productRepository.deleteById(id);
    }

    public Product editProductDetails(Long id, Product product){
        return productRepository.findById(id)
                .map(existingProduct -> {
                    Product productToUpdate = new Product(
                            existingProduct.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getPictureUrl(),
                            product.getCategory(),
                            product.getBrand(),
                            product.getQuantityInStock()
                    );
                    return productRepository.save(productToUpdate);
                })
                .orElseGet(() -> addProductToCatalog(product));
    }
}
