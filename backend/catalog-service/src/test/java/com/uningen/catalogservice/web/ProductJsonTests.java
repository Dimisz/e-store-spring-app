package com.uningen.catalogservice.web;

import com.uningen.catalogservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductJsonTests {
    @Autowired
    private JacksonTester<Product> json;

    @Test
    void testSerialize() throws Exception {
        Product product =
                new Product(
                        1L,
                        "Nike Air",
                        "Perfect shoes for marathon",
                        29.99,
                        "http://someurl.com",
                        "Sportswear",
                        "Nike",
                        5
                );
        var jsonContent = json.write(product);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id")
                .isEqualTo(product.getId().intValue());
        assertThat(jsonContent).extractingJsonPathStringValue("@.name")
                .isEqualTo(product.getName());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description")
                .isEqualTo(product.getDescription());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(product.getPrice());
        assertThat(jsonContent).extractingJsonPathStringValue("@.pictureUrl")
                .isEqualTo(product.getPictureUrl());
        assertThat(jsonContent).extractingJsonPathStringValue("@.category")
                .isEqualTo(product.getCategory());
        assertThat(jsonContent).extractingJsonPathStringValue("@.brand")
                .isEqualTo(product.getBrand());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.quantityInStock")
                .isEqualTo(product.getQuantityInStock());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "id": 1,
                    "name": "Nike Air",
                    "description": "Perfect shoes for marathon",
                    "price": 29.99,
                    "pictureUrl": "http://someurl.com",
                    "category": "Sportswear",
                    "brand": "Nike",
                    "quantityInStock": 5
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Product(
                        1L,
                        "Nike Air",
                        "Perfect shoes for marathon",
                        29.99,
                        "http://someurl.com",
                        "Sportswear",
                        "Nike",
                        5
                ));
    }
}
