package com.belkartspaceapi.dto;

import java.time.LocalDate;

public record CardWithBankDTO(
        Long id,

        String bankName,

        Long number,

        LocalDate term
) {
}
