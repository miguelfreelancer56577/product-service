package com.github.mangelt.productservice.service;

import com.github.mangelt.productservice.dto.ProductRequest;
import com.github.mangelt.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
  void createProduct(ProductRequest productRequest);

  List<ProductResponse> getAllProducts();
}
