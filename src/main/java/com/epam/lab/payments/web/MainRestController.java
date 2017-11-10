package com.epam.lab.payments.web;

import com.epam.lab.payments.dao.BankAccountRepository;
import com.epam.lab.payments.dao.CreditCardRepository;
import com.epam.lab.payments.dao.OrderRepository;
import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.domain.CreditCardEntity;
import com.epam.lab.payments.domain.OrderEntity;
import com.epam.lab.payments.domain.UserEntity;
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
class MainRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "id") Integer userId) {

        Optional<UserEntity> userEntity = Optional.ofNullable(userRepository.findOne(userId));
        if (userEntity.isPresent()) {
            return ResponseEntity.ok().body(userEntity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cards")
    public List<CreditCardEntity> getAllCards() {
        return creditCardRepository.findAll();
    }

    @GetMapping("/orders")
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/accounts")
    public List<BankAccountEntity> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }
}