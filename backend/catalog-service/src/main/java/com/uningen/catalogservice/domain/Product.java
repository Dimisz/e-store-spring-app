package com.uningen.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotNull(message = "Product id should not be null")
    private Long id;
    @NotBlank(message = "Product name should not be blank")
    @NotNull(message = "Product name should not be null")
    private String name;
    @NotBlank(message = "Product description should not be blank")
    @NotNull(message = "Product description should not be null")
    private String description;
//    @NotNull(message = "Product price should not be null")
    @Positive(message = "Product price must be greater than zero")
    private double price;
    @NotBlank(message = "Product picture url should not be blank")
    @NotNull(message = "Product picture url should not be null")
    private String pictureUrl;
    @NotBlank(message = "Product category should not be blank")
    @NotNull(message = "Product category should not be null")
    private String category;
    @NotBlank(message = "Product brand should not be blank")
    @NotNull(message = "Product brand should not be null")
    private String brand;
//    @NotBlank(message = "Product quantity must be defined")
    @Positive(message = "Product quantity must be greater than zero")
    private int quantityInStock;
}
