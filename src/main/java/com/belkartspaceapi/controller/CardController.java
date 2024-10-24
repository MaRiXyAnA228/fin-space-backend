package com.belkartspaceapi.controller;

import com.belkartspaceapi.dto.CardWithBankDTO;
import com.belkartspaceapi.model.User;
import com.belkartspaceapi.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cards")
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardWithBankDTO>> getAllCardsByUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(cardService.findCardsByClientId(user.getClient().getId()));
    }
}
