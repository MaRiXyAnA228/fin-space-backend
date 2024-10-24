package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.dto.ClientWithTransactionsDTO;
import com.belkartspaceapi.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public List<ClientWithTransactionsDTO> findClientsByClientId(Long clientId) {
        return null;
    }

    @Override
    public void addClient(Long clientId, String username) {

    }
}
