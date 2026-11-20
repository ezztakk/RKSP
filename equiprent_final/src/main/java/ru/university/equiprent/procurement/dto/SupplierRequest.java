package ru.university.equiprent.procurement.dto;

import jakarta.validation.constraints.NotBlank;

public record SupplierRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Contact info cannot be blank")
        String contactInfo
) {
}