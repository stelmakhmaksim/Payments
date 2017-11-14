package com.epam.lab.payments.web.api;

import com.epam.lab.payments.dto.BankAccountDTO;
import com.epam.lab.payments.dto.CreditCardDTO;
import com.epam.lab.payments.dto.OrderDTO;
import com.epam.lab.payments.dto.UserDTO;
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
    public List<UserDTO> getAllUsers() {
        return paymentsService.findAllUsers();

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable(value = "id") Integer userId) {
        Optional<UserDTO> userEntity = paymentsService.findOneUser(userId);
        return userEntity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}/cards")
    public ResponseEntity<List<CreditCardDTO>> getCardsByUserId(
            @PathVariable(value = "id") Integer userId) {
        return ResponseEntity.ok(paymentsService.findCardsByUserId(userId));
    }

    @GetMapping("/account/{id}/cards")
    public ResponseEntity<List<CreditCardDTO>> getCardsByAccountId(
            @PathVariable(value = "id") Integer accountId) {
        return ResponseEntity.ok(paymentsService.findCardsByAccountId(accountId));
    }

    @GetMapping("/user/{id}/accounts")
    public ResponseEntity<List<BankAccountDTO>> getAccountsByUserId(
            @PathVariable(value = "id") Integer userId) {
        return ResponseEntity.ok(paymentsService.findAccountsByUserId(userId));
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CreditCardDTO>> getAllCards() {
        return ResponseEntity.ok(paymentsService.findAllCreditCard());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(paymentsService.findAllOrders());
    }

    @GetMapping("/account/{id}/orders")
    public ResponseEntity<List<OrderDTO>> getOrdersByAccountId(
            @PathVariable(value = "id") Integer accountId) {
        return ResponseEntity.ok(paymentsService.findOrdersByAccountId(accountId));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccountDTO>> getAllBankAccounts() {
        return ResponseEntity.ok(paymentsService.findAllBankAccounts());
    }
}
