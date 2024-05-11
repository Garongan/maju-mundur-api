package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.NewProductRequest;
import com.enigma.majumundur.dto.request.UpdateProductRequest;
import com.enigma.majumundur.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(NewProductRequest request);

    ProductResponse getProductById(String id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(UpdateProductRequest request);

    void deleteProduct(String id, String userAccountId);
}
