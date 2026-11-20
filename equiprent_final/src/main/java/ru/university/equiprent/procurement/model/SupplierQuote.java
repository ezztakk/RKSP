package ru.university.equiprent.procurement.model;

import java.math.BigDecimal;

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
public class SupplierQuote {
    private Long id;
    private ProcurementRequest request;
    private Supplier supplier;
    private BigDecimal pricePerUnit;
    private int leadTimeDays;
    private boolean selected;
}