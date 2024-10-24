package com.belkartspaceapi.dto;

import java.time.LocalDate;

public record ClientWithTransactionsDTO(
        Long id,

        String bankName,

        Long number,

        LocalDate term
) {
}
