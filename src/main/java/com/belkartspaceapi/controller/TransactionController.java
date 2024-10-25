package com.belkartspaceapi.controller;

import com.belkartspaceapi.dto.TransactionDTO;
import com.belkartspaceapi.model.User;
import com.belkartspaceapi.service.TransactionService;
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
@RequestMapping("api/transactions")
@Tag(name = "Transaction Controller", description = "API to manage transactions.")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Get All Transactions", description = "Retrieve a list of all transactions associated with the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of transaction."),
            @ApiResponse(responseCode = "401", description = "Unauthorized. User is not authenticated."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(transactionService.findTransactionsByClientId(user.getClient().getId()));
    }
}
