package com.epam.lab.payments.mappers;

import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.dto.BankAccountDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    BankAccountDTO accountToAccountDto(BankAccountEntity account);

    List<BankAccountDTO> accountsToAccountsDto(List<BankAccountEntity> accounts);

    BankAccountEntity accountDtoToAccount(BankAccountDTO account);

    List<BankAccountEntity> accountsDtoToAccounts(List<BankAccountDTO> accounts);
}
