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
import ru.university.equiprent.procurement.dto.ProcurementRequestDto;
import ru.university.equiprent.procurement.dto.ProcurementResponse;
import ru.university.equiprent.procurement.dto.PurchaseOrderResponse;
import ru.university.equiprent.procurement.dto.SupplierQuoteDto;
import ru.university.equiprent.procurement.dto.SupplierQuoteResponse;
import ru.university.equiprent.procurement.model.ProcurementStatus;

@RequestMapping("/api/procurement-requests")
@Tag(name = "ProcurementRequest Controller")
public interface ProcurementRequestApi {

    @GetMapping
    List<ProcurementResponse> getAll(@RequestParam(required = false) ProcurementStatus status);

    @GetMapping("/{id}")
    ProcurementResponse get(@PathVariable Long id);

    @PostMapping
    ProcurementResponse create(@RequestBody @Valid ProcurementRequestDto entity);

    @PutMapping("/{id}")
    ProcurementResponse update(@RequestBody @Valid ProcurementRequestDto entity, @PathVariable Long id);

    @PostMapping("/{id}/quotes")
    SupplierQuoteResponse addQuote(@PathVariable Long id, @RequestBody @Valid SupplierQuoteDto entity);

    @PutMapping("/{id}/select-quote/{quoteId}")
    PurchaseOrderResponse selectQuote(@PathVariable Long id, @PathVariable Long quoteId);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}