package com.belkartspaceapi.dto.mapper;

import com.belkartspaceapi.dto.CardWithBankDTO;
import com.belkartspaceapi.model.Card;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CardWithBankDTOMapper implements Function<Card, CardWithBankDTO> {

    @Override
    public CardWithBankDTO apply(Card card) {
        return new CardWithBankDTO(
                card.getId(),
                card.getBank().getBankName(),
                card.getCardNumber(),
                card.getExpirationDate()
        );
    }
}
