package com.epam.lab.payments.web;

import com.epam.lab.payments.dao.BankAccountRepository;
import com.epam.lab.payments.dao.CreditCardRepository;
import com.epam.lab.payments.dao.OrderRepository;
import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.domain.CreditCardEntity;
import com.epam.lab.payments.domain.OrderEntity;
import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class PaymentsController {

    @Autowired
    PaymentsService paymentsService;

    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        return paymentsService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "id") Integer userId) {

        Optional<UserEntity> userEntity = paymentsService.findOneUser(userId);
        if (userEntity.isPresent()) {
            return ResponseEntity.ok().body(userEntity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CreditCardEntity>> getAllCards() {
        return ResponseEntity.ok().body(paymentsService.findAllCreditCard());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok().body(paymentsService.findAllOrders());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccountEntity>> getAllBankAccounts() {
        return ResponseEntity.ok().body(paymentsService.findAllBankAccounts());
    }
}