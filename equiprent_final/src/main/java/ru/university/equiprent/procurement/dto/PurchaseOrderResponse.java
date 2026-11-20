package ru.university.equiprent.procurement.dto;

import java.time.LocalDate;

import ru.university.equiprent.procurement.model.PurchaseOrderStatus;

public record PurchaseOrderResponse(
        Long id,
        SupplierQuoteResponse acceptedQuote,
        LocalDate orderedAt,
        LocalDate expectedDeliveryDate,
        LocalDate actualDeliveryDate,
        PurchaseOrderStatus status
) {
}