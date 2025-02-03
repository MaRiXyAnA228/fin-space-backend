package com.belkartspaceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(

        Long id,

        BigDecimal amount,

        LocalDateTime transactionDate,

        String placeName,

        String category,

        Long cardNumber
){
}
