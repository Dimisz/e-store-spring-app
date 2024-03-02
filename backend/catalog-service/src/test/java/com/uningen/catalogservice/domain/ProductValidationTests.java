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
        assertThat(violations.iterator().)
    }
}
