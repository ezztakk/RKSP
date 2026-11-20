package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Optional;

import ru.university.equiprent.procurement.model.User;

public interface UserDao {
    User save(User user);
    User update(User user, Long id);
    List<User> findAll();
    void delete(Long id);
    Optional<User> findById(Long id);
}