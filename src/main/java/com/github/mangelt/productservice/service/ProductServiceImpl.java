package com.github.mangelt.productservice.service;

import com.github.mangelt.productservice.dto.ProductRequest;
import com.github.mangelt.productservice.dto.ProductResponse;
import com.github.mangelt.productservice.module.Product;
import com.github.mangelt.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired ProductRepository productRepository;

  @Override
  public void createProduct(final ProductRequest productRequest) {
    final Product product =
        Product.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
            .build();
    productRepository.save(product);
    log.info("Product was stored successfully with id {}", product.getId());
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    log.info("Number of products got from DB: {}", products.size());
    return products.stream()
        .map(
            product ->
                ProductResponse.builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .name(product.getName())
                    .build())
        .collect(Collectors.toList());
  }
}
