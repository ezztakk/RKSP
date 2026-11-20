package ru.university.equiprent.procurement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.university.equiprent.procurement.api.UserApi;
import ru.university.equiprent.procurement.dto.UserRequest;
import ru.university.equiprent.procurement.dto.UserResponse;
import ru.university.equiprent.procurement.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public List<UserResponse> getAll() {
        return service.findAll();
    }

    @Override
    public UserResponse get(Long id) {
        return service.findById(id);
    }

    @Override
    public UserResponse create(UserRequest entity) {
        return service.create(entity);
    }

    @Override
    public UserResponse update(UserRequest entity, Long id) {
        return service.update(entity, id);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}