package com.belkartspaceapi.service.impl;

import com.belkartspaceapi.dto.ClientWithTransactionsDTO;
import com.belkartspaceapi.dto.CreateClientDTO;
import com.belkartspaceapi.dto.mapper.ClientWithTransactionsDTOMapper;
import com.belkartspaceapi.model.ChildClient;
import com.belkartspaceapi.model.Client;
import com.belkartspaceapi.repository.ChildClientRepository;
import com.belkartspaceapi.repository.ClientRepository;
import com.belkartspaceapi.repository.UserRepository;
import com.belkartspaceapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    private final ChildClientRepository childClientRepository;

    private final ClientWithTransactionsDTOMapper clientWithTransactionsDTOMapper;

    @Override
    public List<ClientWithTransactionsDTO> findClientsByClientId(Long clientId) {
        return childClientRepository.findByClientId(clientId)
                .stream()
                .map(clientWithTransactionsDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addClient(Long clientId, CreateClientDTO createClientDTO) {
        Client parentClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Client childClient = userRepository.findClientByUsername(createClientDTO.username())
                .orElseThrow(() -> new IllegalArgumentException("Client not found for the given username"));

        ChildClient child = new ChildClient();
        child.setName(createClientDTO.name());
        child.setClient(parentClient);
        child.setCurrentClient(childClient);
        childClientRepository.save(child);
    }
}
