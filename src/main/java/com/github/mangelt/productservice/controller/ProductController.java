package com.github.mangelt.productservice.controller;

import com.github.mangelt.productservice.dto.ProductRequest;
import com.github.mangelt.productservice.dto.ProductResponse;
import com.github.mangelt.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

  @Autowired protected ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody ProductRequest productRequest) {
    productService.createProduct(productRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> getAll() {
    return productService.getAllProducts();
  }
}
