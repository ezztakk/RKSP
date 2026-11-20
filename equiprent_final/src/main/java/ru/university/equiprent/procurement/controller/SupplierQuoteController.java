package ru.university.equiprent.procurement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.api.SupplierQuoteApi;
import ru.university.equiprent.procurement.dto.SupplierQuoteDto;
import ru.university.equiprent.procurement.dto.SupplierQuoteResponse;
import ru.university.equiprent.procurement.service.SupplierQuoteService;

@RequiredArgsConstructor
@RestController
public class SupplierQuoteController implements SupplierQuoteApi {

    private final SupplierQuoteService service;

    @Override
    public List<SupplierQuoteResponse> getAll() {
        return service.findAll();
    }

    @Override
    public SupplierQuoteResponse get(Long id) {
        return service.findById(id);
    }

    @Override
    public SupplierQuoteResponse create(SupplierQuoteDto entity) {
        return service.create(entity);
    }

    @Override
    public SupplierQuoteResponse update(SupplierQuoteDto entity, Long id) {
        return service.update(entity, id);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}