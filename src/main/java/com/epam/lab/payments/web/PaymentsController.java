package com.epam.lab.payments.web;

import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.domain.CreditCardEntity;
import com.epam.lab.payments.domain.OrderEntity;
import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class PaymentsController {
    private final PaymentsService paymentsService;

    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {

        return paymentsService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "id") Integer userId) {
        Optional<UserEntity> userEntity = paymentsService.findOneUser(userId);
        if (userEntity.isPresent()) {
            return ResponseEntity.ok(userEntity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}/cards")
    public ResponseEntity<List<CreditCardEntity>> getCardsByUserId(@PathVariable(value = "id") Integer userId) {
        return ResponseEntity.ok(paymentsService.findCardsByUserId(userId));
    }

    @GetMapping("/account/{id}/cards")
    public ResponseEntity<List<CreditCardEntity>> getCardsByAccountId(@PathVariable(value = "id") Integer accountId) {
        return ResponseEntity.ok(paymentsService.findCardsByAccountId(accountId));
    }

    @GetMapping("/user/{id}/accounts")
    public ResponseEntity<List<BankAccountEntity>> getAccountsByUserId(@PathVariable(value = "id") Integer userId) {
        return ResponseEntity.ok(paymentsService.findAccountsByUserId(userId));
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CreditCardEntity>> getAllCards() {
        return ResponseEntity.ok(paymentsService.findAllCreditCard());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(paymentsService.findAllOrders());
    }

    @GetMapping("/account/{id}/orders")
    public ResponseEntity<List<OrderEntity>> getOrdersByAccountId(@PathVariable(value = "id") Integer accountId) {
        return ResponseEntity.ok(paymentsService.findOrdersByAccountId(accountId));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccountEntity>> getAllBankAccounts() {
        return ResponseEntity.ok(paymentsService.findAllBankAccounts());
    }
}