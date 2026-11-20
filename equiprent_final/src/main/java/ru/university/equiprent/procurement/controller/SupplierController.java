package ru.university.equiprent.procurement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.api.SupplierApi;
import ru.university.equiprent.procurement.dto.SupplierRequest;
import ru.university.equiprent.procurement.dto.SupplierResponse;
import ru.university.equiprent.procurement.service.SupplierService;

@RequiredArgsConstructor
@RestController
public class SupplierController implements SupplierApi {

    private final SupplierService service;

    @Override
    public List<SupplierResponse> getAll(String sortBy) {
        return service.findAll(sortBy);
    }

    @Override
    public SupplierResponse get(Long id) {
        return service.findById(id);
    }

    @Override
    public SupplierResponse create(SupplierRequest entity) {
        return service.create(entity);
    }

    @Override
    public SupplierResponse update(SupplierRequest entity, Long id) {
        return service.update(entity, id);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}