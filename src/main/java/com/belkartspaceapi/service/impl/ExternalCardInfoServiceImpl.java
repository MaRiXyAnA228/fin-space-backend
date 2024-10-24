package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.dto.CardDetailsDTO;
import com.belkartspaceapi.model.Bank;
import com.belkartspaceapi.repository.BankRepository;
import com.belkartspaceapi.service.ExternalCardInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ExternalCardInfoServiceImpl implements ExternalCardInfoService {

    private final BankRepository bankRepository;

    @Override
    public Optional<CardDetailsDTO> getCardDetails(Long cardNumber, String expirationDate) {
        List<Bank> banks = bankRepository.findAll();
        if (banks.isEmpty()) {
            return Optional.empty();
        }
        int randomIndex = new Random().nextInt(banks.size());
        return Optional.of(new CardDetailsDTO(banks.get(randomIndex)));
    }
}
