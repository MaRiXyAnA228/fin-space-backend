package com.belkartspaceapi.service;

import com.belkartspaceapi.dto.ClientWithTransactionsDTO;
import com.belkartspaceapi.dto.CreateClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientWithTransactionsDTO> findClientsByClientId(Long clientId);

    void addClient(Long clientId, CreateClientDTO createClientDTO);
}
