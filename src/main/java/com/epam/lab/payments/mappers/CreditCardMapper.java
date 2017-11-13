package com.epam.lab.payments.mappers;

import com.epam.lab.payments.domain.CreditCardEntity;
import com.epam.lab.payments.dto.CreditCardDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    List<CreditCardDTO> cardsToCardsDto(List<CreditCardEntity> cards);

    CreditCardDTO cardToCardDto(CreditCardEntity card);
}
