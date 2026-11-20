package ru.university.equiprent.procurement.model;

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
public class Supplier {
    private Long id;
    private String name;
    private String contactInfo;
    
    // рейтинг от 0 до 5. падает если поставщик опаздывает
    private double reliabilityScore; 
}