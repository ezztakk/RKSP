package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import ru.university.equiprent.procurement.model.Supplier;

@Repository
public class SupplierRepository implements SupplierDao {

    private final Map<Long, Supplier> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public Supplier save(Supplier supplier) {
        if (supplier.getId() == null) {
            supplier.setId(sequence.incrementAndGet());
        }
        storage.put(supplier.getId(), supplier);
        return supplier;
    }

    @Override
    public Supplier update(Supplier supplier, Long id) {
        supplier.setId(id);
        storage.put(id, supplier);
        return supplier;
    }

    @Override
    public List<Supplier> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }
}