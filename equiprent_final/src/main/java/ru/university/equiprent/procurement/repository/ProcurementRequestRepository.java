package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import ru.university.equiprent.procurement.model.ProcurementRequest;

@Repository
public class ProcurementRequestRepository implements ProcurementRequestDao {

    private final Map<Long, ProcurementRequest> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public ProcurementRequest save(ProcurementRequest request) {
        if (request.getId() == null) {
            request.setId(sequence.incrementAndGet());
        }
        storage.put(request.getId(), request);
        return request;
    }

    @Override
    public ProcurementRequest update(ProcurementRequest request, Long id) {
        request.setId(id);
        storage.put(id, request);
        return request;
    }

    @Override
    public List<ProcurementRequest> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<ProcurementRequest> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }
}