
package com.uningen.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for running",
                        new BigDecimal(29.99),
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIdIsNullThenValidationFails(){
        Product product =
                new Product(
                        null,
                        "Running Shoes",
                        "Perfect shoes for running",
                        new BigDecimal(29.99),
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product id should not be null");
    }

    @Test
    void whenNameIsBlankThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "",
                        "Perfect shoes for running",
                        new BigDecimal(29.99),
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product name should not be blank");
    }

    @Test
    void whenNameIsNullThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        null,
                        "Perfect shoes for running",
                        new BigDecimal(29.99),
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(2);
    }

    @Test
    void whenDescriptionIsBlankThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "",
                        new BigDecimal(29.99),
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product description should not be blank");
    }

    @Test
    void whenDescriptionIsNullThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        null,
                        new BigDecimal(29.99),
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(2);
    }

    @Test
    void whenPriceIsNullThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        null,
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product price should not be null");
    }

    @Test
    void whenPriceIsNegativeThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(-29.99),
                        "http://somepic.com",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product price must be greater than zero");
    }

    @Test
    void whenPictureUrlIsBlankThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(29.99),
                        "",
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product picture url should not be blank");
    }

    @Test
    void whenPictureUrlIsNullThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(29.99),
                        null,
                        "Sportwear",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(2);
    }

    @Test
    void whenCategoryIsBlankThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(29.99),
                        "http://someurl.com",
                        "",
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product category should not be blank");
    }

    @Test
    void whenCategoryIsNullThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(29.99),
                        "http://someurl.com",
                        null,
                        "Nike",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(2);
    }

    @Test
    void whenBrandIsBlankThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(29.99),
                        "http://someurl.com",
                        "Sportswear",
                        "",
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product brand should not be blank");
    }

    @Test
    void whenBrandIsNullThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(29.99),
                        "http://someurl.com",
                        "Sportswear",
                        null,
                        5
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(2);
    }

    @Test
    void whenQuantityInStockIsNegativeThenValidationFails(){
        Product product =
                new Product(
                        1L,
                        "Running Shoes",
                        "Perfect shoes for marathon",
                        new BigDecimal(29.99),
                        "http://someurl.com",
                        "Sportswear",
                        "Nike",
                        -10
                );
        Set<ConstraintViolation<Product>> violations =
                validator.validate(product);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Product quantity must be greater than zero");
    }
}
