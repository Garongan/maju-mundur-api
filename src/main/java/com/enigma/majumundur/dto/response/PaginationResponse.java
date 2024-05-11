package com.enigma.majumundur.dto.response;

public record PaginationResponse(
        Integer totalPages,
        Long totalElements,
        Integer currentPage,
        Integer pageSize,
        Boolean hasNext,
        Boolean hasPrevious
) {
}
