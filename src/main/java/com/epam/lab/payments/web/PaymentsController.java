package com.epam.lab.payments.web;

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
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer userId) {
        Optional<UserDTO> userEntity = paymentsService.findOneUser(userId);
        if (userEntity.isPresent()) {
            return ResponseEntity.ok(userEntity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CreditCardDTO>> getAllCards() {
        return ResponseEntity.ok(paymentsService.findAllCreditCard());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(paymentsService.findAllOrders());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccountDTO>> getAllBankAccounts() {
        return ResponseEntity.ok(paymentsService.findAllBankAccounts());
    }
}
