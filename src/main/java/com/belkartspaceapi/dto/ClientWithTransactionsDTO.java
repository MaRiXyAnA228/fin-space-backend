package com.belkartspaceapi.dto;

import java.util.List;

public record ClientWithTransactionsDTO(

        Long id,

        String childClientName,

        List<TransactionsToChildClientDTO> transactions
) {
}
