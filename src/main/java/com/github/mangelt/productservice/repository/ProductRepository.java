package com.github.mangelt.productservice.repository;

import com.github.mangelt.productservice.module.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}
