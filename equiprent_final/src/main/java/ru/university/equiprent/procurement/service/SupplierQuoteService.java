package ru.university.equiprent.procurement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.dto.ProcurementResponse;
import ru.university.equiprent.procurement.dto.SupplierQuoteDto;
import ru.university.equiprent.procurement.dto.SupplierQuoteResponse;
import ru.university.equiprent.procurement.dto.SupplierResponse;
import ru.university.equiprent.procurement.dto.UserResponse;
import ru.university.equiprent.procurement.model.ProcurementRequest;
import ru.university.equiprent.procurement.model.Supplier;
import ru.university.equiprent.procurement.model.SupplierQuote;
import ru.university.equiprent.procurement.repository.ProcurementRequestRepository;
import ru.university.equiprent.procurement.repository.SupplierQuoteRepository;
import ru.university.equiprent.procurement.repository.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierQuoteService {

    private final SupplierQuoteRepository repository;
    private final ProcurementRequestRepository requestRepository;
    private final SupplierRepository supplierRepository;

    public List<SupplierQuoteResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public SupplierQuoteResponse findById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SupplierQuote not found: " + id)));
    }

    public SupplierQuoteResponse create(SupplierQuoteDto dto) {
        if (dto.requestId() == null) {
            throw new RuntimeException("requestId cannot be null");
        }

        ProcurementRequest request = requestRepository.findById(dto.requestId())
                .orElseThrow(() -> new RuntimeException("ProcurementRequest not found: " + dto.requestId()));

        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found: " + dto.supplierId()));

        SupplierQuote quote = SupplierQuote.builder()
                .request(request)
                .supplier(supplier)
                .pricePerUnit(dto.pricePerUnit())
                .leadTimeDays(dto.leadTimeDays())
                .selected(false)
                .build();

        return toResponse(repository.save(quote));
    }

    public SupplierQuoteResponse update(SupplierQuoteDto dto, Long id) {
        SupplierQuote quote = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SupplierQuote not found: " + id));

        ProcurementRequest request = requestRepository.findById(dto.requestId())
                .orElseThrow(() -> new RuntimeException("ProcurementRequest not found: " + dto.requestId()));

        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found: " + dto.supplierId()));

        quote.setRequest(request);
        quote.setSupplier(supplier);
        quote.setPricePerUnit(dto.pricePerUnit());
        quote.setLeadTimeDays(dto.leadTimeDays());

        return toResponse(repository.update(quote, id));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SupplierQuote not found: " + id));
        repository.delete(id);
    }

    private SupplierQuoteResponse toResponse(SupplierQuote quote) {
        ProcurementRequest req = quote.getRequest();
        UserResponse userResponse = new UserResponse(
                req.getRequestedBy().getId(),
                req.getRequestedBy().getUsername(),
                req.getRequestedBy().getEmail());
        ProcurementResponse requestResponse = new ProcurementResponse(
                req.getId(),
                req.getItemDescription(),
                req.getQuantity(),
                req.getNeededBy(),
                userResponse,
                req.getStatus());

        Supplier s = quote.getSupplier();
        SupplierResponse supplierResponse = new SupplierResponse(
                s.getId(),
                s.getName(),
                s.getContactInfo(),
                s.getReliabilityScore());

        return new SupplierQuoteResponse(
                quote.getId(),
                requestResponse,
                supplierResponse,
                quote.getPricePerUnit(),
                quote.getLeadTimeDays(),
                quote.isSelected());
    }
}