package com.example.replicadb.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CountryRequest(
        @NotBlank
        String name
) {
}
