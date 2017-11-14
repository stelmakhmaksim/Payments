package com.epam.lab.payments.services;

import com.epam.lab.payments.dao.BankAccountRepository;
import com.epam.lab.payments.dao.CreditCardRepository;
import com.epam.lab.payments.dao.OrderRepository;
import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.dto.BankAccountDTO;
import com.epam.lab.payments.dto.CreditCardDTO;
import com.epam.lab.payments.dto.OrderDTO;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.mappers.BankAccountMapper;
import com.epam.lab.payments.mappers.CreditCardMapper;
import com.epam.lab.payments.mappers.OrderMapper;
import com.epam.lab.payments.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        return userMapper.usersToUsersDto(userRepository.findAll());
    }

    public Optional<UserDTO> findOneUser(Integer userId) {
        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findOne(userId)));
    }

    public List<CreditCardDTO> findAllCreditCard() {
        return cardMapper.cardsToCardsDto(creditCardRepository.findAll());
    }

    public Optional<UserDTO> findUserByEmail(String email) {
        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findByEmail(email)));
    }

    public List<CreditCardDTO> findCardsByUserId(Integer userId) {
        return cardMapper.cardsToCardsDto(creditCardRepository.findByUserId(userId));
    }

    public List<CreditCardDTO> findCardsByAccountId(Integer accountId) {
        return cardMapper.cardsToCardsDto(creditCardRepository.findByAccountId(accountId));
    }

    public List<BankAccountDTO> findAccountsByUserId(Integer userId) {
        return accountMapper.accountsToAccountsDto(bankAccountRepository.findByUserId(userId));
    }

    public List<BankAccountDTO> findAllBankAccounts() {
        return accountMapper.accountsToAccountsDto(bankAccountRepository.findAll());
    }

    public List<OrderDTO> findAllOrders() {
        return orderMapper.ordersToOrdersDto(orderRepository.findAll());
    }

    public List<OrderDTO> findOrdersByAccountId(Integer accountId) {
        return orderMapper.ordersToOrdersDto(orderRepository.findByAccountId(accountId));
    }

    public void update(BankAccountDTO accountDTO) {
        BankAccountEntity oldAccount = bankAccountRepository.findOne(accountDTO.getId());
        oldAccount.setBlocked(accountDTO.isBlocked());
        bankAccountRepository.save(oldAccount);
    }

    public void create(OrderDTO orderDTO) {
        orderRepository.save(orderMapper.orderDtoToOrder(orderDTO));
    }
}
