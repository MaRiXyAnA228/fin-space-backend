package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.dto.CardWithBankDTO;
import com.belkartspaceapi.dto.mapper.CardWithBankDTOMapper;
import com.belkartspaceapi.repository.CardRepository;
import com.belkartspaceapi.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardWithBankDTOMapper cardWithBankDTOMapper;

    @Override
    public List<CardWithBankDTO> findCardsByClientId(Long clientId) {
        return cardRepository.findAllByClientId(clientId)
                .stream()
                .map(cardWithBankDTOMapper)
                .collect(Collectors.toList());
    }
}
