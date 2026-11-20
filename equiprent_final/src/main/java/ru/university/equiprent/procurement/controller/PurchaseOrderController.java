package ru.university.equiprent.procurement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.api.PurchaseOrderApi;
import ru.university.equiprent.procurement.dto.PurchaseOrderResponse;
import ru.university.equiprent.procurement.dto.ReceiveRequest;
import ru.university.equiprent.procurement.service.PurchaseOrderService;

@RequiredArgsConstructor
@RestController
public class PurchaseOrderController implements PurchaseOrderApi {

    private final PurchaseOrderService service;

    @Override
    public List<PurchaseOrderResponse> getAll() {
        return service.findAll();
    }

    @Override
    public PurchaseOrderResponse get(Long id) {
        return service.findById(id);
    }

    @Override
    public PurchaseOrderResponse receive(Long id, ReceiveRequest entity) {
        // здесь происходит фиксация поставки и пересчет рейтинга
        return service.receive(id, entity);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}