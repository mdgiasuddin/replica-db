package com.example.replicadb.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRequest(
        @NotBlank
        String name,
        @NotNull
        Long countryId
) {
}
