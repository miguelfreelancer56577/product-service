package com.github.mangelt.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mangelt.productservice.dto.ProductRequest;
import com.github.mangelt.productservice.module.Product;
import com.github.mangelt.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Slf4j
class ProductServiceApplicationTests {

  @Container
  protected static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

  @Autowired protected MockMvc mockMvc;
  @Autowired protected ObjectMapper objectMapper;
  @Autowired ProductRepository productRepository;

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Test
  void testCreateProduct() throws Exception {
    ProductRequest productRequest =
        ProductRequest.builder().name("iPhone 13").description("Iphone 13").price("1200").build();
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest)))
        .andExpect(status().isCreated());
    List<Product> products = productRepository.findAll();
    log.info("Products: {}", products);
    Assertions.assertEquals(1, products.size());
  }
}
