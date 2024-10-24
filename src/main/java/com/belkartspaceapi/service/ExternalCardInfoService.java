package com.belkartspaceapi.service;

import com.belkartspaceapi.dto.CardDetailsDTO;

import java.util.Optional;

public interface ExternalCardInfoService {

    Optional<CardDetailsDTO> getCardDetails(Long cardNumber, String expirationDate);
}
