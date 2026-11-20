package ru.university.equiprent.procurement.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.university.equiprent.procurement.dto.SupplierRequest;
import ru.university.equiprent.procurement.dto.SupplierResponse;

@RequestMapping("/api/suppliers")
@Tag(name = "Supplier Controller")
public interface SupplierApi {

    @GetMapping
    List<SupplierResponse> getAll(@RequestParam(required = false) String sortBy);

    @GetMapping("/{id}")
    SupplierResponse get(@PathVariable Long id);

    @PostMapping
    SupplierResponse create(@RequestBody @Valid SupplierRequest entity);

    @PutMapping("/{id}")
    SupplierResponse update(@RequestBody @Valid SupplierRequest entity, @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}