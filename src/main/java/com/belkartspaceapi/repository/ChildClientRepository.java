package com.belkartspaceapi.repository;

import com.belkartspaceapi.model.ChildClient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildClientRepository extends JpaRepository<ChildClient, Long> {
    @EntityGraph(attributePaths = {
            "currentClient",
            "currentClient.cards",
            "currentClient.cards.transactions",
            "currentClient.cards.transactions.place"
    })
    List<ChildClient> findByClientId(Long clientId);
}
