package com.epam.lab.payments.services;

import com.epam.lab.payments.dao.BankAccountRepository;
import com.epam.lab.payments.dao.CreditCardRepository;
import com.epam.lab.payments.dao.OrderRepository;
import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.domain.CreditCardEntity;
import com.epam.lab.payments.domain.OrderEntity;
import com.epam.lab.payments.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j
public class PaymentsService {
    private final UserRepository userRepository;

    private final CreditCardRepository creditCardRepository;

    private final OrderRepository orderRepository;

    private final BankAccountRepository bankAccountRepository;

    public List<UserEntity> findAllUsers() {
        log.info("All users were successfully found");
        return userRepository.findAll();
    }

    public Optional<UserEntity> findOneUser(Integer userId) {
        log.info("User was successfully found");
        return Optional.ofNullable(userRepository.findOne(userId));
    }

    public List<CreditCardEntity> findAllCreditCard() {
        log.info("All credit cards were successfully found");
        return creditCardRepository.findAll();
    }

    public List<OrderEntity> findAllOrders() {
        log.info("All orders were successfully found");
        return orderRepository.findAll();
    }


    public List<BankAccountEntity> findAllBankAccounts() {
        log.info("All bank accounts were successfully found");
        return bankAccountRepository.findAll();
    }

    public List<CreditCardEntity> findCardsByUserId(Integer userId) {
        log.info("All cards by userId were successfully found");
        return creditCardRepository.findByUserId(userId);
    }

    public List<CreditCardEntity> findCardsByAccountId(Integer accountId) {
        log.info("All cards by accountId were successfully found");
        return creditCardRepository.findByAccountId(accountId);
    }

    public List<BankAccountEntity> findAccountsByUserId(Integer userId) {
        log.info("All accounts by userId were successfully found");
        return bankAccountRepository.findByUserId(userId);
    }

    public List<OrderEntity> findOrdersByAccountId(Integer accountId) {
        log.info("All orders by AccountId were successfully found");
        return orderRepository.findByAccountId(accountId);
    }
}
