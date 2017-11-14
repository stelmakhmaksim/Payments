package com.epam.lab.payments.services;

import com.epam.lab.payments.dao.BankAccountRepository;
import com.epam.lab.payments.dao.CreditCardRepository;
import com.epam.lab.payments.dao.OrderRepository;
import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.dto.BankAccountDTO;
import com.epam.lab.payments.dto.CreditCardDTO;
import com.epam.lab.payments.dto.OrderDTO;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.mappers.BankAccountMapper;
import com.epam.lab.payments.mappers.CreditCardMapper;
import com.epam.lab.payments.mappers.OrderMapper;
import com.epam.lab.payments.mappers.UserMapper;
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

    private final UserMapper userMapper;

    private final CreditCardMapper cardMapper;

    private final BankAccountMapper accountMapper;

    private final OrderMapper orderMapper;


    public List<UserDTO> findAllUsers() {
        log.info("All users were successfully found");
        return userMapper.usersToUsersDto(userRepository.findAll());
    }

    public Optional<UserDTO> findOneUser(Integer userId) {
        log.info("User was successfully found");
        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findOne(userId)));
    }

    public List<CreditCardDTO> findAllCreditCard() {
        log.info("All credit cards were successfully found");
        return cardMapper.cardsToCardsDto(creditCardRepository.findAll());
    }

    public Optional<UserDTO> findUserByEmail(String email) {
        log.info("All users by email were successfully found");
        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findByEmail(email)));
    }

    public List<CreditCardDTO> findCardsByUserId(Integer userId) {
        log.info("All cards by userId were successfully found");
        return cardMapper.cardsToCardsDto(creditCardRepository.findByUserId(userId));
    }

    public List<CreditCardDTO> findCardsByAccountId(Integer accountId) {
        log.info("All cards by accountId were successfully found");
        return cardMapper.cardsToCardsDto(creditCardRepository.findByAccountId(accountId));
    }

    public List<BankAccountDTO> findAccountsByUserId(Integer userId) {
        log.info("All accounts by userId were successfully found");
        return accountMapper.accountsToAccountsDto(bankAccountRepository.findByUserId(userId));
    }

    public List<BankAccountDTO> findAllBankAccounts() {
        log.info("All bank accounts were successfully found");
        return accountMapper.accountsToAccountsDto(bankAccountRepository.findAll());
    }

    public List<OrderDTO> findAllOrders() {
        log.info("All orders were successfully found");
        return orderMapper.ordersToOrdersDto(orderRepository.findAll());
    }

    public List<OrderDTO> findOrdersByAccountId(Integer accountId) {
        log.info("All orders by AccountId were successfully found");
        return orderMapper.ordersToOrdersDto(orderRepository.findByAccountId(accountId));
    }
}
