package com.belkartspaceapi.controller;

import com.belkartspaceapi.model.User;
import com.belkartspaceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user, null);
        return ResponseEntity.ok("User registered successfully");
    }
}
