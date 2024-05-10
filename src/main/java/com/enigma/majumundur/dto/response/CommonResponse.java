package com.enigma.majumundur.dto.response;

public record CommonResponse<T>(
        Integer statusCode,
        String message,
        T data,
        PaginationResponse paginationResponse) {
}
