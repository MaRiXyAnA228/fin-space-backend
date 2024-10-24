package com.belkartspaceapi.service;

import com.belkartspaceapi.dto.CardWithBankDTO;

import java.util.List;

public interface CardService {

    List<CardWithBankDTO> findCardsByClientId(Long clientId);
}
