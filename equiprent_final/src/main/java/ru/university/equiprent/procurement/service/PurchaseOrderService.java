package ru.university.equiprent.procurement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.dto.PurchaseOrderResponse;
import ru.university.equiprent.procurement.dto.ReceiveRequest;
import ru.university.equiprent.procurement.model.PurchaseOrder;
import ru.university.equiprent.procurement.model.PurchaseOrderStatus;
import ru.university.equiprent.procurement.model.ProcurementStatus;
import ru.university.equiprent.procurement.repository.ProcurementRequestRepository;
import ru.university.equiprent.procurement.repository.PurchaseOrderRepository;
import ru.university.equiprent.procurement.repository.SupplierQuoteRepository;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository repository;
    private final ProcurementRequestRepository requestRepository;
    private final SupplierQuoteRepository quoteRepository;
    private final SupplierQuoteService quoteService;

    public List<PurchaseOrderResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public PurchaseOrderResponse findById(Long id) {
        var order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заказ с id " + id + " не найден"));
        return toResponse(order);
    }

    public PurchaseOrderResponse selectQuote(Long requestId, Long quoteId) {
        var request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));

        var quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Предложение не найдено"));

        if (!quote.getRequest().getId().equals(requestId)) {
            throw new RuntimeException("Предложение от другой заявки");
        }
        if (request.getStatus() != ProcurementStatus.OPEN) {
            throw new RuntimeException("Заявка уже не OPEN");
        }

        quote.setSelected(true);

        // срок доставки берем из предложения
        var today = LocalDate.now();
        var order = PurchaseOrder.builder()
                .acceptedQuote(quote)
                .orderedAt(today)
                .expectedDeliveryDate(today.plusDays(quote.getLeadTimeDays()))
                .status(PurchaseOrderStatus.PENDING)
                .build();

        request.setStatus(ProcurementStatus.ORDERED);

        return toResponse(repository.save(order));
    }

    public PurchaseOrderResponse receive(Long orderId, ReceiveRequest request) {
        var order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        if (order.getStatus() != PurchaseOrderStatus.PENDING) {
            throw new RuntimeException("Заказ уже обработан");
        }

        var actualDate = request.actualDeliveryDate();
        order.setActualDeliveryDate(actualDate);

        boolean onTime = !actualDate.isAfter(order.getExpectedDeliveryDate());
        order.setStatus(onTime ? PurchaseOrderStatus.DELIVERED : PurchaseOrderStatus.DELAYED);

        // пересчет: 90% старого + 10% нового (5.0 если в срок, 1.0 если опоздал)
        var supplier = order.getAcceptedQuote().getSupplier();
        double oldScore = supplier.getReliabilityScore();
        double deliveryScore = onTime ? 5.0 : 1.0;
        supplier.setReliabilityScore(oldScore * 0.9 + deliveryScore * 0.1);

        order.getAcceptedQuote().getRequest().setStatus(ProcurementStatus.CLOSED);

        return toResponse(repository.update(order, orderId));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));
        repository.delete(id);
    }

    private PurchaseOrderResponse toResponse(PurchaseOrder order) {
        return new PurchaseOrderResponse(
                order.getId(),
                quoteService.findById(order.getAcceptedQuote().getId()),
                order.getOrderedAt(),
                order.getExpectedDeliveryDate(),
                order.getActualDeliveryDate(),
                order.getStatus()
        );
    }
}