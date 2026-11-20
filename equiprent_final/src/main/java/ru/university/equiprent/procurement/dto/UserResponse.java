package ru.university.equiprent.procurement.dto;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}