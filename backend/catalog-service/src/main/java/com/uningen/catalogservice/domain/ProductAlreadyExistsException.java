package com.uningen.catalogservice.domain;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(Long id){
        super("A product with id " + id + " already exists.");
    }
}
