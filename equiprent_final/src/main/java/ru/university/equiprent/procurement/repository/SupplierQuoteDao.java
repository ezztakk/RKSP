package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Optional;

import ru.university.equiprent.procurement.model.SupplierQuote;

public interface SupplierQuoteDao {
    SupplierQuote save(SupplierQuote quote);
    SupplierQuote update(SupplierQuote quote, Long id);
    List<SupplierQuote> findAll();
    void delete(Long id);
    Optional<SupplierQuote> findById(Long id);
}