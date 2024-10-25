package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.dto.TransactionDTO;
import com.belkartspaceapi.dto.mapper.TransactionDTOMapper;
import com.belkartspaceapi.model.Card;
import com.belkartspaceapi.repository.CardRepository;
import com.belkartspaceapi.repository.TransactionRepository;
import com.belkartspaceapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final CardRepository cardRepository;

    private final TransactionDTOMapper transactionDTOMapper;

    @Override
    public List<TransactionDTO> findTransactionsByClientId(Long clientId) {
        List<Card> cards = cardRepository.findAllByClientId(clientId);

        List<Long> cardIds = cards.stream()
                .map(Card::getId)
                .collect(Collectors.toList());

        return transactionRepository.findAllByCardIds(cardIds)
                .stream()
                .map(transactionDTOMapper)
                .collect(Collectors.toList());
    }
}
