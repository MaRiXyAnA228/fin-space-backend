package com.belkartspaceapi.controller;

import com.belkartspaceapi.dto.CardWithBankDTO;
import com.belkartspaceapi.dto.CreateCardDTO;
import com.belkartspaceapi.model.User;
import com.belkartspaceapi.service.CardService;
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
@RequestMapping("api/cards")
@Tag(name = "Card Controller", description = "API to manage cards.")
public class CardController {

    private final CardService cardService;

    @Operation(summary = "Get All Cards", description = "Retrieve a list of all cards associated with the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of cards."),
            @ApiResponse(responseCode = "401", description = "Unauthorized. User is not authenticated."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping
    public ResponseEntity<List<CardWithBankDTO>> getAllCardsByUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(cardService.findCardsByClientId(user.getClient().getId()));
    }

    @Operation(summary = "Add a Card", description = "Add a new card associated with the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card added successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid card data provided."),
            @ApiResponse(responseCode = "401", description = "Unauthorized. User is not authenticated."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping
    public ResponseEntity<String> addCard(@AuthenticationPrincipal User user, @RequestBody CreateCardDTO createCardDTO) {
        cardService.addCard(user.getClient().getId(), createCardDTO);
        return ResponseEntity.status(201).body("Card added successfully");
    }
}
