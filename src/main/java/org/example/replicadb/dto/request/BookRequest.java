package org.example.replicadb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotNull
        Double price
) {
}
