package ru.university.equiprent.procurement.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import ru.university.equiprent.procurement.model.User;

@Repository
public class UserRepository implements UserDao {

    private final Map<Long, User> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(sequence.incrementAndGet());
        }
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user, Long id) {
        user.setId(id);
        storage.put(id, user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }
}