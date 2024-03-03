package com.uningen.catalogservice;

import com.uningen.catalogservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenProductCreated(){
		Product expectedProduct = new Product(
				1L,
				"Nike Air",
				"Perfect shoes for marathon",
				29.99,
				"http://someurl.com",
				"Sportswear",
				"Nike",
				5
		);
		webTestClient
				.post()
				.uri("/products")
				.bodyValue(expectedProduct)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Product.class).value(actualProduct -> {
					assertThat(actualProduct).isNotNull();
					assertThat(actualProduct.getId())
							.isEqualTo(expectedProduct.getId());
					assertThat(actualProduct.getName())
							.isEqualTo(expectedProduct.getName());
					assertThat(actualProduct.getDescription())
							.isEqualTo(expectedProduct.getDescription());
					assertThat(actualProduct.getPrice())
							.isEqualTo(expectedProduct.getPrice());
					assertThat(actualProduct.getPictureUrl())
							.isEqualTo(expectedProduct.getPictureUrl());
					assertThat(actualProduct.getCategory())
							.isEqualTo(expectedProduct.getCategory());
					assertThat(actualProduct.getBrand())
							.isEqualTo(expectedProduct.getBrand());
					assertThat(actualProduct.getQuantityInStock())
							.isEqualTo(expectedProduct.getQuantityInStock());
				});
	}
	@Test
	void contextLoads() {
	}

}
