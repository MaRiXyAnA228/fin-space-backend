package com.belkartspaceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CreateCardDTO(

        @JsonProperty("card_number")
        Long cardNumber,

        @JsonProperty("expiration_date")
        LocalDate expirationDate
) {
}
