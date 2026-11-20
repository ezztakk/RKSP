package ru.university.equiprent.procurement.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {
    private Long id;
    private SupplierQuote acceptedQuote;
    private LocalDate orderedAt;
    private LocalDate expectedDeliveryDate;
    private LocalDate actualDeliveryDate;
    private PurchaseOrderStatus status;
}