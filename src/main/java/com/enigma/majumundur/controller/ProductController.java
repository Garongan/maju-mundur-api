package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.NewProductRequest;
import com.enigma.majumundur.dto.request.UpdateProductRequest;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.PaginationResponse;
import com.enigma.majumundur.dto.response.ProductResponse;
import com.enigma.majumundur.mapper.PaginationResponseMapper;
import com.enigma.majumundur.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrl.PRODUCT)
public class ProductController {
    private final ProductService productService;
    private final PaginationResponseMapper paginationResponseMapper;

    @Operation(summary = "Create new product")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('MERCHANT') and isAuthenticated()")
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<ProductResponse>> createProduct(@RequestBody NewProductRequest request) {
        ProductResponse productResponse = productService.createProduct(request);
        CommonResponse<ProductResponse> commonResponse = new CommonResponse<>(
                HttpStatus.CREATED.value(),
                StatusMessage.SUCCESS_CREATED,
                productResponse,
                null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @Operation(summary = "Get product by id")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<ProductResponse>> getProductById(@PathVariable String id) {
        ProductResponse productResponse = productService.getProductById(id);
        CommonResponse<ProductResponse> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS,
                productResponse,
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Operation(summary = "Get all products")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<List<ProductResponse>>> getAllProducts(
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "name") String orderBy,
            @RequestParam(required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Page<ProductResponse> productResponsePage = productService.getAllProducts(
                direction,
                orderBy,
                currentPage,
                pageSize
        );
        PaginationResponse paginationResponse = paginationResponseMapper.apply(productResponsePage);
        CommonResponse<List<ProductResponse>> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS_RETRIEVE_LIST,
                productResponsePage.getContent(),
                paginationResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Operation(summary = "Update product")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('MERCHANT') and isAuthenticated()")
    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<ProductResponse>> updateProduct(@RequestBody UpdateProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(request);
        CommonResponse<ProductResponse> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS_UPDATED,
                productResponse,
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Operation(summary = "Delete product")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('MERCHANT') and isAuthenticated()")
    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<String>> deleteProduct(
            @PathVariable String id,
            @RequestParam String userAccountId
    ) {
        productService.deleteProduct(id, userAccountId);
        CommonResponse<String> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS_DELETED,
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
