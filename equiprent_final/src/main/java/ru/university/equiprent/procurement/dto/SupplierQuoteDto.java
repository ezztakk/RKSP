package ru.university.equiprent.procurement.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SupplierQuoteDto(
        Long requestId,

        @NotNull(message = "supplierId cannot be null")
        Long supplierId,

        @NotNull(message = "pricePerUnit cannot be null")
        @DecimalMin(value = "0.01", message = "pricePerUnit must be positive")
        BigDecimal pricePerUnit,

        @Positive(message = "leadTimeDays must be positive")
        int leadTimeDays
) {
}