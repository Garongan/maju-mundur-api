package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.request.NewTransactionDetailRequest;
import com.enigma.majumundur.entity.Product;
import com.enigma.majumundur.entity.TransactionDetail;
import com.enigma.majumundur.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransactionDetailMapper implements Function<NewTransactionDetailRequest, TransactionDetail> {

    private final ProductService productService;

    @Override
    public TransactionDetail apply(NewTransactionDetailRequest newTransactionDetailRequest) {
        Product product = productService.getOneProductById(newTransactionDetailRequest.productId());
        return TransactionDetail.builder()
                .quantity(newTransactionDetailRequest.quantity())
                .price(newTransactionDetailRequest.price())
                .product(product)
                .build();
    }
}
