package ru.university.equiprent.procurement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.dto.ProcurementRequestDto;
import ru.university.equiprent.procurement.dto.ProcurementResponse;
import ru.university.equiprent.procurement.dto.UserResponse;
import ru.university.equiprent.procurement.model.ProcurementRequest;
import ru.university.equiprent.procurement.model.ProcurementStatus;
import ru.university.equiprent.procurement.model.User;
import ru.university.equiprent.procurement.repository.ProcurementRequestRepository;
import ru.university.equiprent.procurement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProcurementRequestService {

    private final ProcurementRequestRepository repository;
    private final UserRepository userRepository;

    public List<ProcurementResponse> findAll(ProcurementStatus status) {
        List<ProcurementRequest> requests = repository.findAll();
        if (status != null) {
            requests = requests.stream()
                    .filter(r -> r.getStatus() == status)
                    .toList();
        }
        return requests.stream().map(this::toResponse).toList();
    }

    public ProcurementResponse findById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProcurementRequest not found: " + id)));
    }

    public ProcurementResponse create(ProcurementRequestDto dto) {
        User user = userRepository.findById(dto.requestedByUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.requestedByUserId()));

        ProcurementRequest request = ProcurementRequest.builder()
                .itemDescription(dto.itemDescription())
                .quantity(dto.quantity())
                .neededBy(dto.neededBy())
                .requestedBy(user)
                .status(ProcurementStatus.OPEN)
                .build();

        return toResponse(repository.save(request));
    }

    public ProcurementResponse update(ProcurementRequestDto dto, Long id) {
        ProcurementRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProcurementRequest not found: " + id));

        User user = userRepository.findById(dto.requestedByUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.requestedByUserId()));

        request.setItemDescription(dto.itemDescription());
        request.setQuantity(dto.quantity());
        request.setNeededBy(dto.neededBy());
        request.setRequestedBy(user);

        return toResponse(repository.update(request, id));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProcurementRequest not found: " + id));
        repository.delete(id);
    }

    private ProcurementResponse toResponse(ProcurementRequest request) {
        UserResponse userResponse = new UserResponse(
                request.getRequestedBy().getId(),
                request.getRequestedBy().getUsername(),
                request.getRequestedBy().getEmail());

        return new ProcurementResponse(
                request.getId(),
                request.getItemDescription(),
                request.getQuantity(),
                request.getNeededBy(),
                userResponse,
                request.getStatus());
    }
}