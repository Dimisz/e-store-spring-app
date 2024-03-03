package com.uningen.catalogservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uningen.catalogservice.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    private Long productId = 1L;
    private Product validProduct =
            new Product(
                    productId,
                    "Nike Air",
                    "Perfect shoes for marathon",
                    29.99,
                    "http://someurl.com",
                    "Sportswear",
                    "Nike",
                    5
            );
    Product invalidProduct =
            new Product(
                    1L,
                    null,
                    "Perfect shoes for marathon",
                    29.99,
                    "http://someurl.com",
                    "Sportswear",
                    "Nike",
                    5
            );

    @Test
    void whenGetProductNotExistingThenShouldReturn404() throws Exception {
        Long nonExistingId = 1001L;
        given(productService.viewProductDetails(nonExistingId))
                .willThrow(ProductNotFoundException.class);
        mockMvc
                .perform(get("/products/" + nonExistingId))
                .andExpect(status().isNotFound());
    }

    // POST
    @Test
    void whenPostProductThenShouldReturn201() throws Exception{
        mockMvc
                .perform(post("/products")
                        .content(asJsonString(validProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void whenPostInvalidProductThenShouldReturn400() throws Exception{
        mockMvc
                .perform(post("/products")
                        .content(asJsonString(invalidProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPostExistingProductThenShouldReturn422() throws Exception{

        given(productService.addProductToCatalog(validProduct))
                .willThrow(ProductAlreadyExistsException.class);

        mockMvc
                .perform(post("/products")
                        .content(asJsonString(validProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    // PUT
    @Test
    void whenPutValidProductThenShouldReturn200() throws Exception{

        mockMvc
                .perform(put("/products/{id}", productId)
                        .content(asJsonString(validProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenPutInvalidProductThenShouldReturn400() throws Exception{

        mockMvc
                .perform(put("/products/{id}", invalidProduct.getId())
                        .content(asJsonString(invalidProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // DELETE
    void whenDeleteProductThenShouldReturn204() throws Exception{
        mockMvc.perform(delete("/products/{id}", validProduct.getId()))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
