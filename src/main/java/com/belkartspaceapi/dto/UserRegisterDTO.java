package com.belkartspaceapi.dto;


public record UserRegisterDTO(

        String username,

        String password,

        String firstName,

        String lastName
) {
}
