package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.dto.CardWithBankDTO;
import com.belkartspaceapi.dto.CreateCardDTO;
import com.belkartspaceapi.dto.mapper.CardWithBankDTOMapper;
import com.belkartspaceapi.model.Bank;
import com.belkartspaceapi.model.Card;
import com.belkartspaceapi.model.Client;
import com.belkartspaceapi.repository.CardRepository;
import com.belkartspaceapi.repository.ClientRepository;
import com.belkartspaceapi.service.CardService;
import com.belkartspaceapi.service.ExternalCardInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardWithBankDTOMapper cardWithBankDTOMapper;

    private final ClientRepository clientRepository;

    private final ExternalCardInfoService externalCardInfoService;

    @Override
    public List<CardWithBankDTO> findCardsByClientId(Long clientId) {
        return cardRepository.findAllByClientId(clientId)
                .stream()
                .map(cardWithBankDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addCard(Long clientId, CreateCardDTO createCardDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Bank bank = externalCardInfoService.getCardDetails(createCardDTO.cardNumber(), createCardDTO.expirationDate().toString())
                .orElseThrow(() -> new IllegalArgumentException("Bank not found")).bank();

        Card card = new Card();
        card.setCardNumber(createCardDTO.cardNumber());
        card.setExpirationDate(createCardDTO.expirationDate());
        card.setClient(client);
        card.setBank(bank);

        cardRepository.save(card);
    }
}
