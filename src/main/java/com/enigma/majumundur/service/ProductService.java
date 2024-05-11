package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.NewProductRequest;
import com.enigma.majumundur.dto.request.UpdateProductRequest;
import com.enigma.majumundur.dto.response.ProductResponse;
import com.enigma.majumundur.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse createProduct(NewProductRequest request);

    ProductResponse getProductById(String id);

    Product getOneProductById(String id);

    Page<ProductResponse> getAllProducts(
            String direction,
            String orderBy,
            Integer currentPage,
            Integer pageSize
    );

    ProductResponse updateProduct(UpdateProductRequest request);

    void deleteProduct(String id, String userAccountId);
}
