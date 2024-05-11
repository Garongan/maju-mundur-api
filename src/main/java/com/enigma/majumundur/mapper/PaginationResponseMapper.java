package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.PaginationResponse;
import com.enigma.majumundur.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PaginationResponseMapper implements Function<Page<ProductResponse>, PaginationResponse> {
    @Override
    public PaginationResponse apply(Page<ProductResponse> productResponses) {
        return new PaginationResponse(
                productResponses.getTotalPages(),
                productResponses.getTotalElements(),
                productResponses.getNumber(),
                productResponses.getSize(),
                productResponses.hasNext(),
                productResponses.hasPrevious()
        );
    }
}
