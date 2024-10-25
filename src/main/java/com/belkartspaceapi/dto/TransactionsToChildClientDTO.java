package com.belkartspaceapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionsToChildClientDTO (

        Long id,

        BigDecimal amount,

        LocalDateTime transactionDate,

        String placeName,

        Long cardNumber
){
}
