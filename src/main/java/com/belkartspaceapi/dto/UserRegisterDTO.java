package com.belkartspaceapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRegisterDTO(

        String username,

        String password,

        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName
) {
}
