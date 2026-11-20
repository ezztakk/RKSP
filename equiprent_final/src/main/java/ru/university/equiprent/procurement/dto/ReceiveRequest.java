package ru.university.equiprent.procurement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ReceiveRequest(
        @NotNull(message = "actualDeliveryDate cannot be null")
        LocalDate actualDeliveryDate
) {
}