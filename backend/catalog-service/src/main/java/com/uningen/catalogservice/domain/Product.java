package com.uningen.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotBlank(message = "Product id must be defined")
    private Long id;
    @NotBlank(message = "Product name must be defined")
    private String name;
    @NotBlank(message = "Product description must be defined")
    private String description;
    @NotBlank(message = "Product price must be defined")
    @Positive(message = "Product price must be greater than zero")
    private BigDecimal price;
    @NotBlank(message = "Product picture url must be defined")
    private String pictureUrl;
    @NotBlank(message = "Product category must be defined")
    private String category;
    @NotBlank(message = "Product brand must be defined")
    private String brand;
    @NotBlank(message = "Product quantity must be defined")
    @Positive(message = "Product quantity must be greater than zero")
    private int quantityInStock;
}
