package ru.university.equiprent.procurement.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.university.equiprent.procurement.dto.PurchaseOrderResponse;
import ru.university.equiprent.procurement.dto.ReceiveRequest;

@RequestMapping("/api/purchase-orders")
@Tag(name = "PurchaseOrder Controller")
public interface PurchaseOrderApi {

    @GetMapping
    List<PurchaseOrderResponse> getAll();

    @GetMapping("/{id}")
    PurchaseOrderResponse get(@PathVariable Long id);

    @PutMapping("/{id}/receive")
    PurchaseOrderResponse receive(@PathVariable Long id, @RequestBody @Valid ReceiveRequest entity);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}