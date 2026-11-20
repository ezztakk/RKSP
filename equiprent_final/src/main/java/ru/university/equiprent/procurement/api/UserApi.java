package ru.university.equiprent.procurement.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.university.equiprent.procurement.dto.UserRequest;
import ru.university.equiprent.procurement.dto.UserResponse;

@RequestMapping("/api/users")
@Tag(name = "User Controller")
public interface UserApi {

    @GetMapping
    List<UserResponse> getAll();

    @GetMapping("/{id}")
    UserResponse get(@PathVariable Long id);

    @PostMapping
    UserResponse create(@RequestBody @Valid UserRequest entity);

    @PutMapping("/{id}")
    UserResponse update(@RequestBody @Valid UserRequest entity, @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}