package ru.university.equiprent.procurement.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.dto.SupplierRequest;
import ru.university.equiprent.procurement.dto.SupplierResponse;
import ru.university.equiprent.procurement.model.Supplier;
import ru.university.equiprent.procurement.repository.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository repository;

    public List<SupplierResponse> findAll(String sortBy) {
        List<Supplier> suppliers = repository.findAll();

        // сортировка по рейтингу (по убыванию)
        if ("reliabilityScore".equalsIgnoreCase(sortBy)) {
            suppliers.sort(Comparator.comparingDouble(Supplier::getReliabilityScore).reversed());
        }

        return suppliers.stream().map(this::toResponse).toList();
    }

    public SupplierResponse findById(Long id) {
        var supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Поставщик с id " + id + " не найден"));
        return toResponse(supplier);
    }

    public SupplierResponse create(SupplierRequest request) {
        var supplier = Supplier.builder()
                .name(request.name())
                .contactInfo(request.contactInfo())
                .reliabilityScore(5.0) // ставим максимальный рейтинг новым
                .build();
        return toResponse(repository.save(supplier));
    }

    public SupplierResponse update(SupplierRequest request, Long id) {
        var supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Поставщик не найден"));

        supplier.setName(request.name());
        supplier.setContactInfo(request.contactInfo());
        return toResponse(repository.update(supplier, id));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Поставщик не найден"));
        repository.delete(id);
    }

    private SupplierResponse toResponse(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactInfo(),
                supplier.getReliabilityScore()
        );
    }
}