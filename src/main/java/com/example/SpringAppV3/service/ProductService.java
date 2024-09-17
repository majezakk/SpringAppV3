package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.ProductModel;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductModel> getAllProducts();
    Optional<ProductModel> getProductById(Long id);
    ProductModel saveProduct(ProductModel product);
    void deleteProduct(Long id);
}