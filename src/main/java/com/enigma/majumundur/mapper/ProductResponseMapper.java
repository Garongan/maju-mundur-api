package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.ProductResponse;
import com.enigma.majumundur.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductResponseMapper implements Function<Product, ProductResponse> {
    private final MerchantResponseMapper merchantResponseMapper;

    @Override
    public ProductResponse apply(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                merchantResponseMapper.apply(product.getMerchant())
        );
    }
}
