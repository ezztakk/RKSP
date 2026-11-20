package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import ru.university.equiprent.procurement.model.SupplierQuote;

@Repository
public class SupplierQuoteRepository implements SupplierQuoteDao {

    private final Map<Long, SupplierQuote> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public SupplierQuote save(SupplierQuote quote) {
        if (quote.getId() == null) {
            quote.setId(sequence.incrementAndGet());
        }
        storage.put(quote.getId(), quote);
        return quote;
    }

    @Override
    public SupplierQuote update(SupplierQuote quote, Long id) {
        quote.setId(id);
        storage.put(id, quote);
        return quote;
    }

    @Override
    public List<SupplierQuote> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<SupplierQuote> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }
}