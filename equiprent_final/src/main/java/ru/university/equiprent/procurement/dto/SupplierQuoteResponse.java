package ru.university.equiprent.procurement.dto;

import java.math.BigDecimal;

public record SupplierQuoteResponse(
        Long id,
        ProcurementResponse request,
        SupplierResponse supplier,
        BigDecimal pricePerUnit,
        int leadTimeDays,
        boolean selected
) {
}