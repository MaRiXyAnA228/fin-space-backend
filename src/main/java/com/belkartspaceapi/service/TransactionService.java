package com.belkartspaceapi.service;

import com.belkartspaceapi.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> findTransactionsByClientId(Long clientId);
}
