package com.belkartspaceapi.service;

import com.belkartspaceapi.dto.CardWithBankDTO;
import com.belkartspaceapi.dto.CreateCardDTO;

import java.util.List;

public interface CardService {

    List<CardWithBankDTO> findCardsByClientId(Long clientId);

    void addCard(Long clientId, CreateCardDTO createCardDTO);
}
