package ru.university.equiprent.procurement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProcurementRequestDto(
        @NotBlank(message = "Item description cannot be blank")
        String itemDescription,

        @Positive(message = "Quantity must be positive")
        int quantity,

        @NotNull(message = "neededBy cannot be null")
        LocalDate neededBy,

        @NotNull(message = "requestedByUserId cannot be null")
        Long requestedByUserId
) {
}