package com.belkartspaceapi.service;

import com.belkartspaceapi.dto.ClientWithTransactionsDTO;

import java.util.List;

public interface ClientService {

    List<ClientWithTransactionsDTO> findClientsByClientId(Long clientId);

    void addClient(Long clientId, String username);
}
