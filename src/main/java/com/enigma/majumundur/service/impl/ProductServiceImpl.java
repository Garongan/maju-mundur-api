package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.NewProductRequest;
import com.enigma.majumundur.dto.request.UpdateProductRequest;
import com.enigma.majumundur.dto.response.ProductResponse;
import com.enigma.majumundur.entity.Merchant;
import com.enigma.majumundur.entity.Product;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.mapper.ProductResponseMapper;
import com.enigma.majumundur.repository.ProductRepository;
import com.enigma.majumundur.service.MerchantService;
import com.enigma.majumundur.service.ProductService;
import com.enigma.majumundur.service.UserService;
import com.enigma.majumundur.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final MerchantService merchantService;
    private final ProductResponseMapper productResponseMapper;
    private final UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductResponse createProduct(NewProductRequest request) {
        validationUtil.validate(request);

        Merchant merchant = merchantService.getMerchantByUserAccountId(request.userAccountId());

        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .stock(request.stock())
                .merchant(merchant)
                .build();
        return productResponseMapper.apply(productRepository.saveAndFlush(product));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(String id) {
        return productResponseMapper.apply(productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> getAllProducts(
            String direction,
            String orderBy,
            Integer currentPage,
            Integer pageSize
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        if (currentPage <= 0) currentPage = 1;
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

        return productRepository.findAll(pageable).map(productResponseMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductResponse updateProduct(UpdateProductRequest request) {
        validationUtil.validate(request);

        Product product = productRepository.findById(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(request.userAccountId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, StatusMessage.FORBIDDEN_ACCESS);
        }

        Merchant merchant = merchantService.getMerchantByUserAccountId(request.userAccountId());

        Product updatedProduct = Product.builder()
                .id(product.getId())
                .name(request.name())
                .price(request.price())
                .stock(request.stock())
                .merchant(merchant)
                .build();

        return productResponseMapper.apply(productRepository.saveAndFlush(updatedProduct));
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteProduct(String id, String userAccountId) {
        ProductResponse productResponse = getProductById(id);
        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(userAccountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, StatusMessage.FORBIDDEN_ACCESS);
        }
        productRepository.deleteById(productResponse.id());
    }
}
