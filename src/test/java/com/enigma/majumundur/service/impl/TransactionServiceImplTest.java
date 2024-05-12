package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.dto.request.NewTransactionDetailRequest;
import com.enigma.majumundur.dto.request.NewTransactionRequest;
import com.enigma.majumundur.dto.response.TransactionResponse;
import com.enigma.majumundur.entity.*;
import com.enigma.majumundur.mapper.TransactionDetailResponseMapper;
import com.enigma.majumundur.mapper.TransactionResponseMapper;
import com.enigma.majumundur.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private CustomerService customerService;
    @Mock
    private final TransactionDetailResponseMapper transactionDetailResponseMapper = new TransactionDetailResponseMapper();
    @Mock
    private final TransactionResponseMapper transactionResponseMapper = new TransactionResponseMapper(transactionDetailResponseMapper);
    @Mock
    private UserService userService;
    @Mock
    private MerchantService merchantService;
    @Mock
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldReturnTransactionResponseWhenCreateTransaction() {
        // given
        NewTransactionDetailRequest newTransactionDetailRequest = new NewTransactionDetailRequest(
                1,
                1000L,
                "example-product-id",
                "example-reward-id"
        );
        NewTransactionRequest newTransactionRequest = new NewTransactionRequest(
                1,
                "example-customer-id",
                List.of(newTransactionDetailRequest)
        );
        UserAccount userAccount = UserAccount.builder()
                .id("example-user-account-id")
                .username("example-username")
                .password("example-password")
                .roles(List.of(Role.builder().role(UserRole.ROLE_CUSTOMER).build()))
                .build();
        Customer expectedCustomer = Customer.builder()
                .id("example-customer-id")
                .name("example-customer-name")
                .phone("example-customer-phone")
                .address("example-customer-address")
                .point(10)
                .userAccount(userAccount)
                .build();
        Product product = Product.builder()
                .id("example-product-id")
                .name("example-product-name")
                .price(1000L)
                .stock(1)
                .build();

        List<TransactionDetail> transactionDetails = List.of(new TransactionDetail(
                "example-transaction-detail-id",
                1,
                1000L,
                product,
                null
        ));
        Transaction transaction = Transaction.builder()
                .id("example-transaction-id")
                .transDate(new Date(Instant.now().toEpochMilli()))
                .customer(expectedCustomer)
                .transactionDetails(transactionDetails)
                .build();
        transaction.setTransactionDetails(transactionDetails
                .stream()
                .peek(transactionDetail -> transactionDetail.setTransaction(transaction))
                .toList());

        TransactionResponse expectedTransactionResponse = transactionResponseMapper.apply(transaction);

        // stubbing
        Mockito
                .when(customerService.getCustomerById("example-customer-id"))
                .thenReturn(expectedCustomer);

        // when
        Customer actualCustomer = customerService.getCustomerById("example-customer-id");
        TransactionResponse actualTransactionResponse = transactionService.createTransaction(newTransactionRequest);

        // then
        assertEquals(expectedCustomer, actualCustomer);
        assertEquals(expectedTransactionResponse, actualTransactionResponse);
    }

    @Test
    void shouldReturnListTransactionResponseWhenGetAllTransactions() {
        // given
        UserAccount userAccount = UserAccount.builder()
                .id("example-user-account-id")
                .username("example-username")
                .password("example-password")
                .roles(List.of(Role.builder().role(UserRole.ROLE_CUSTOMER).build()))
                .build();
        Customer expectedCustomer = Customer.builder()
                .id("example-customer-id")
                .name("example-customer-name")
                .phone("example-customer-phone")
                .address("example-customer-address")
                .point(10)
                .userAccount(userAccount)
                .build();
        Product product = Product.builder()
                .id("example-product-id")
                .name("example-product-name")
                .price(1000L)
                .stock(1)
                .build();

        List<TransactionDetail> transactionDetails = List.of(new TransactionDetail(
                "example-transaction-detail-id",
                1,
                1000L,
                product,
                null
        ));
        Transaction transaction = Transaction.builder()
                .id("example-transaction-id")
                .transDate(new Date(Instant.now().toEpochMilli()))
                .customer(expectedCustomer)
                .transactionDetails(transactionDetails)
                .build();
        transaction.setTransactionDetails(transactionDetails
                .stream()
                .peek(transactionDetail -> transactionDetail.setTransaction(transaction))
                .toList());

        List<Transaction> transactionList = List.of(transaction);

        List<TransactionResponse> expectedTransactionResponse = transactionList.stream().map(
                t -> new TransactionResponse(
                        t.getId(),
                        t.getTransDate().toString(),
                        t.getCustomer().getPoint(),
                        t.getCustomer().getId(),
                        t.getTransactionDetails().stream().map(transactionDetailResponseMapper).toList()
                )
        ).toList();

        // stubbing
        Mockito
                .when(transactionService.getAllTransactions())
                .thenReturn(expectedTransactionResponse);

        // when
        List<TransactionResponse> actualTransactionResponse = transactionService.getAllTransactions();

        // then
        assertEquals(expectedTransactionResponse, actualTransactionResponse);
    }

    @Test
    void getMerchantHistoryTransaction() {
        // given
        UserAccount expectedUserAccount = UserAccount.builder()
                .id("example-user-account-id")
                .username("example-username")
                .password("example-password")
                .roles(List.of(Role.builder().role(UserRole.ROLE_CUSTOMER).build()))
                .build();

        Merchant expectedMerchant = Merchant.builder()
                .id("example-merchant-id")
                .shopName("example-shop-name")
                .phone("example-phone")
                .address("example-address")
                .userAccount(expectedUserAccount)
                .build();

        Customer expectedCustomer = Customer.builder()
                .id("example-customer-id")
                .name("example-customer-name")
                .phone("example-customer-phone")
                .address("example-customer-address")
                .point(10)
                .build();
        Product product = Product.builder()
                .id("example-product-id")
                .name("example-product-name")
                .price(1000L)
                .stock(1)
                .merchant(expectedMerchant)
                .build();

        List<TransactionDetail> transactionDetails = List.of(new TransactionDetail(
                "example-transaction-detail-id",
                1,
                1000L,
                product,
                null
        ));
        Transaction transaction = Transaction.builder()
                .id("example-transaction-id")
                .transDate(new Date(Instant.now().toEpochMilli()))
                .customer(expectedCustomer)
                .transactionDetails(transactionDetails)
                .build();
        transaction.setTransactionDetails(transactionDetails
                .stream()
                .peek(transactionDetail -> transactionDetail.setTransaction(transaction))
                .toList());

        List<Transaction> transactionList = List.of(transaction);

        List<TransactionResponse> expectedHistoryMerchantTransaction = transactionList.stream().map(
                t -> new TransactionResponse(
                        t.getId(),
                        t.getTransDate().toString(),
                        t.getCustomer().getPoint(),
                        t.getCustomer().getId(),
                        t.getTransactionDetails().stream().map(transactionDetailResponseMapper).toList()
                )
        ).toList();

        // stubbing
        Mockito
                .when(userService.getByContext())
                .thenReturn(expectedUserAccount);
        Mockito
                .when(merchantService.getMerchantByUserAccountId("example-user-account-id"))
                .thenReturn(expectedMerchant);
        Mockito
                .when(transactionService.getMerchantHistoryTransaction())
                .thenReturn(expectedHistoryMerchantTransaction);

        // when
        UserAccount actualUserAccount = userService.getByContext();
        Merchant actualMerchant = merchantService.getMerchantByUserAccountId("example-user-account-id");
        List<TransactionResponse> actualHistoryMerchantTransaction = transactionService.getMerchantHistoryTransaction();

        // then
        assertEquals(expectedUserAccount, actualUserAccount);
        assertEquals(expectedMerchant, actualMerchant);
        assertEquals(expectedHistoryMerchantTransaction, actualHistoryMerchantTransaction);
    }
}