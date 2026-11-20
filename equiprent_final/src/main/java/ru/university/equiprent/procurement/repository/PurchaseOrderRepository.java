package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import ru.university.equiprent.procurement.model.PurchaseOrder;

@Repository
public class PurchaseOrderRepository implements PurchaseOrderDao {

    private final Map<Long, PurchaseOrder> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public PurchaseOrder save(PurchaseOrder order) {
        if (order.getId() == null) {
            order.setId(sequence.incrementAndGet());
        }
        storage.put(order.getId(), order);
        return order;
    }

    @Override
    public PurchaseOrder update(PurchaseOrder order, Long id) {
        order.setId(id);
        storage.put(id, order);
        return order;
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<PurchaseOrder> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }
}