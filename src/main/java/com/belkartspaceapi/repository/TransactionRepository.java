package com.belkartspaceapi.repository;

import com.belkartspaceapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.card.id IN :cardIds")
    List<Transaction> findAllByCardIds(@Param("cardIds") List<Long> cardIds);
}
