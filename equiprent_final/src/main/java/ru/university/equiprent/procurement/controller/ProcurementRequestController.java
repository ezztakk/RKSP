package ru.university.equiprent.procurement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.api.ProcurementRequestApi;
import ru.university.equiprent.procurement.dto.ProcurementRequestDto;
import ru.university.equiprent.procurement.dto.ProcurementResponse;
import ru.university.equiprent.procurement.dto.PurchaseOrderResponse;
import ru.university.equiprent.procurement.dto.SupplierQuoteDto;
import ru.university.equiprent.procurement.dto.SupplierQuoteResponse;
import ru.university.equiprent.procurement.model.ProcurementStatus;
import ru.university.equiprent.procurement.service.ProcurementRequestService;
import ru.university.equiprent.procurement.service.PurchaseOrderService;
import ru.university.equiprent.procurement.service.SupplierQuoteService;

@RequiredArgsConstructor
@RestController
public class ProcurementRequestController implements ProcurementRequestApi {

    private final ProcurementRequestService service;
    private final PurchaseOrderService purchaseOrderService;
    private final SupplierQuoteService quoteService;

    @Override
    public List<ProcurementResponse> getAll(ProcurementStatus status) {
        return service.findAll(status);
    }

    @Override
    public ProcurementResponse get(Long id) {
        return service.findById(id);
    }

    @Override
    public ProcurementResponse create(ProcurementRequestDto entity) {
        return service.create(entity);
    }

    @Override
    public ProcurementResponse update(ProcurementRequestDto entity, Long id) {
        return service.update(entity, id);
    }

    @Override
    public SupplierQuoteResponse addQuote(Long id, SupplierQuoteDto entity) {
        // принудительно ставим requestId из пути, чтобы не присылали левые id в теле
        SupplierQuoteDto fixed = new SupplierQuoteDto(
                id,
                entity.supplierId(),
                entity.pricePerUnit(),
                entity.leadTimeDays());
        return quoteService.create(fixed);
    }

    @Override
    public PurchaseOrderResponse selectQuote(Long id, Long quoteId) {
        return purchaseOrderService.selectQuote(id, quoteId);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}