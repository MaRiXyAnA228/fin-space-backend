package com.belkartspaceapi.controller;

import com.belkartspaceapi.dto.ClientWithTransactionsDTO;
import com.belkartspaceapi.dto.CreateClientDTO;
import com.belkartspaceapi.model.User;
import com.belkartspaceapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/clients")
@Tag(name = "Card Controller", description = "API to manage clients.")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Get All Clients", description = "Retrieve a list of all clients associated with the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of clients."),
            @ApiResponse(responseCode = "401", description = "Unauthorized. User is not authenticated."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping
    public ResponseEntity<List<ClientWithTransactionsDTO>> getAllClientsByUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(clientService.findClientsByClientId(user.getClient().getId()));
    }

    @Operation(summary = "Add a Client", description = "Add a new client associated with the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client added successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid client data provided."),
            @ApiResponse(responseCode = "401", description = "Unauthorized. User is not authenticated."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping
    public ResponseEntity<String> addClient(@AuthenticationPrincipal User user, @RequestBody CreateClientDTO createClientDTO) {
        clientService.addClient(user.getClient().getId(), createClientDTO);
        return ResponseEntity.status(201).body("Client added successfully");
    }
}
