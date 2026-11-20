package ru.university.equiprent.procurement.dto;

public record SupplierResponse(
        Long id,
        String name,
        String contactInfo,
        double reliabilityScore
) {
}