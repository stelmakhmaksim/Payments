package com.epam.lab.payments.services;

import com.epam.lab.payments.dao.BankAccountRepository;
import com.epam.lab.payments.dao.CreditCardRepository;
import com.epam.lab.payments.dao.OrderRepository;
import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.domain.CreditCardEntity;
import com.epam.lab.payments.domain.OrderEntity;
import com.epam.lab.payments.domain.UserEntity;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;

@Service
public class PaymentsService {
    private final UserRepository userRepository;

    private final CreditCardRepository creditCardRepository;

    private final OrderRepository orderRepository;

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public PaymentsService(UserRepository userRepository, CreditCardRepository creditCardRepository, OrderRepository orderRepository, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
        this.orderRepository = orderRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findOneUser(Integer userId) {
        return Optional.ofNullable(userRepository.findOne(userId));
    }


    public List<CreditCardEntity> findAllCreditCard() {
        return creditCardRepository.findAll();
    }

    public List<OrderEntity> findAllOrders() {
        return orderRepository.findAll();
    }


    public List<BankAccountEntity> findAllBankAccounts() {
        return bankAccountRepository.findAll();
    }
}
