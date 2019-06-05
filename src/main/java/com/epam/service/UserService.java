package com.epam.service;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.repo.UserRepoImpl;
import lombok.SneakyThrows;

public class UserService {
    private final UserRepoImpl userRepo = new UserRepoImpl();

    @SneakyThrows
    public User save(User item) throws IllegalArgumentException {
        return userRepo.save(item);
    }

    @SneakyThrows
    public User removeById(Long id) throws IllegalArgumentException {
        return userRepo.removeById(id);
    }

    @SneakyThrows
    public User findById(Long id) throws IllegalArgumentException {
        return userRepo.findById(id);
    }

    @SneakyThrows
    public User update(User item) throws IllegalArgumentException {
        return userRepo.update(item);
    }

    @SneakyThrows
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }
}
