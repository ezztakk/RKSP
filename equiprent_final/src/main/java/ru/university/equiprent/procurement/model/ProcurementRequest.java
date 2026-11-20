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
public class ProcurementRequest {
    private Long id;
    private String itemDescription;
    private int quantity;
    private LocalDate neededBy;
    private User requestedBy;
    
    // OPEN -> ORDERED -> CLOSED
    private ProcurementStatus status; 
}