package com.belkartspaceapi.dto;

import java.time.LocalDate;

public record CreateCardDTO(

        Long cardNumber,

        LocalDate expirationDate
) {
}
