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

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.university.equiprent.procurement.dto.SupplierQuoteDto;
import ru.university.equiprent.procurement.dto.SupplierQuoteResponse;

@RequestMapping("/api/supplier-quotes")
@Tag(name = "SupplierQuote Controller")
public interface SupplierQuoteApi {

    @GetMapping
    List<SupplierQuoteResponse> getAll();

    @GetMapping("/{id}")
    SupplierQuoteResponse get(@PathVariable Long id);

    @PostMapping
    SupplierQuoteResponse create(@RequestBody @Valid SupplierQuoteDto entity);

    @PutMapping("/{id}")
    SupplierQuoteResponse update(@RequestBody @Valid SupplierQuoteDto entity, @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}