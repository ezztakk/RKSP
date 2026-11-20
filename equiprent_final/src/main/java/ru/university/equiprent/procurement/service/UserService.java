package ru.university.equiprent.procurement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.dto.UserRequest;
import ru.university.equiprent.procurement.dto.UserResponse;
import ru.university.equiprent.procurement.model.User;
import ru.university.equiprent.procurement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id)));
    }

    public UserResponse create(UserRequest request) {
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .build();
        return toResponse(repository.save(user));
    }

    public UserResponse update(UserRequest request, Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setUsername(request.username());
        user.setEmail(request.email());
        return toResponse(repository.update(user, id));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        repository.delete(id);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}