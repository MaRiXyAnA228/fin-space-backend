package com.belkartspaceapi.dto.mapper;

import com.belkartspaceapi.dto.TransactionDTO;
import com.belkartspaceapi.model.Transaction;
import com.belkartspaceapi.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TransactionDTOMapper implements Function<Transaction, TransactionDTO> {

    @Override
    public TransactionDTO apply(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getPlace().getName(),
                transaction.getCategory(),
                transaction.getCard().getCardNumber()
        );
    }
}
