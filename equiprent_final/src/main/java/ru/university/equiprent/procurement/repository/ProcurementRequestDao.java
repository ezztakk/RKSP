package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Optional;

import ru.university.equiprent.procurement.model.ProcurementRequest;

public interface ProcurementRequestDao {
    ProcurementRequest save(ProcurementRequest request);
    ProcurementRequest update(ProcurementRequest request, Long id);
    List<ProcurementRequest> findAll();
    void delete(Long id);
    Optional<ProcurementRequest> findById(Long id);
}