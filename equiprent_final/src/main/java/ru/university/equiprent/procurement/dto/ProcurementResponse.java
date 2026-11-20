package ru.university.equiprent.procurement.dto;

import java.time.LocalDate;

import ru.university.equiprent.procurement.model.ProcurementStatus;

public record ProcurementResponse(
        Long id,
        String itemDescription,
        int quantity,
        LocalDate neededBy,
        UserResponse requestedBy,
        ProcurementStatus status
) {
}