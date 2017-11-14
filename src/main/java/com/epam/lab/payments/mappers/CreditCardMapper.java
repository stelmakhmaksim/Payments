package com.epam.lab.payments.mappers;

import com.epam.lab.payments.domain.CreditCardEntity;
import com.epam.lab.payments.dto.CreditCardDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    CreditCardDTO cardToCardDto(CreditCardEntity card);

    List<CreditCardDTO> cardsToCardsDto(List<CreditCardEntity> cards);

    CreditCardEntity cardDtoToCard(CreditCardDTO card);

    List<CreditCardEntity> cardsDtoToCards(List<CreditCardDTO> cards);

}
