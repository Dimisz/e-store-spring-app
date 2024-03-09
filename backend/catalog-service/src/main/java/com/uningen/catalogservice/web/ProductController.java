package com.uningen.catalogservice.web;

import com.uningen.catalogservice.domain.Product;
import com.uningen.catalogservice.domain.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getAllProducts(){
        return productService.viewProductList();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Long id){
        return productService.viewProductDetails(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@Valid @RequestBody Product product){
        return productService.addProductToCatalog(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        productService.removeProductFromCatalog(id);
    }

    @PutMapping("{id}")
    public Product putBook(@PathVariable Long id, @Valid @RequestBody Product product){
        return productService.editProductDetails(id, product);
    }
}
