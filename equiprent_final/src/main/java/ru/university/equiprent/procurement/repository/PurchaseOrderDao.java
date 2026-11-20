package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Optional;

import ru.university.equiprent.procurement.model.PurchaseOrder;

public interface PurchaseOrderDao {
    PurchaseOrder save(PurchaseOrder order);
    PurchaseOrder update(PurchaseOrder order, Long id);
    List<PurchaseOrder> findAll();
    void delete(Long id);
    Optional<PurchaseOrder> findById(Long id);
}