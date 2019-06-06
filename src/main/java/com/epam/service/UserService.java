package com.epam.service;

import com.epam.domain.Bill;
import com.epam.domain.User;
import com.epam.repo.Repository;
import com.epam.repo.UserRepoImpl;
import com.epam.state.RepositoryState;
import java.util.Collections;
import lombok.SneakyThrows;

public class UserService {

    private final Repository<User, Long> userRepo;
    private Repository<Bill, Long> billRepo = RepositoryState.getBillRepositoryInstance();

    public UserService(Repository<User, Long> userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Check user and user fields by null
     * @param user to check
     * @throws IllegalArgumentException if accepted user or user fields is null
     */
    private void checkNulls(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("Accepted user is null!");
        }
        if (findById(user.getId()) != null) {
            throw new IllegalArgumentException("User with same id already exist!");
        }
        if (user.getLogin() == null){
            throw new IllegalArgumentException("Login is not correct!");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("User have to be either ADMIN or USER");
        }
        if (user.getBills() == null) {
            throw new IllegalArgumentException("User don't have bills");
        }
    }

    /**
     * Accept user, check by null and save it in repository
     * @param user
     * @return saved user
     * @throws IllegalArgumentException if accepted user or user fields is null
     */
    @SneakyThrows
    public User save(User user) throws IllegalArgumentException {
        return userRepo.save(user);
    }

    /**
     * Accepted id and try to delete user with same id
     * @param id
     * @return removed user or null, if user with same id does not exist
     * @throws IllegalArgumentException if id is null
     */
    @SneakyThrows
    public User removeById(Long id) throws IllegalArgumentException {
        return userRepo.removeById(id);
    }

    /**
     * Accept id and try to find user with same id
     * @param id
     * @return found user or null, if user with same id does not exist
     * @throws IllegalArgumentException if id is null
     */
    @SneakyThrows
    public User findById(Long id) throws IllegalArgumentException {
        return userRepo.findById(id);
    }

    /**
     * Update user
     * @param user
     * @return updated user or null if same user not exist in repository
     * @throws IllegalArgumentException if accepted user or user fields is null
     */
    @SneakyThrows
    public User update(User user) throws IllegalArgumentException {
        return userRepo.update(user);
    }

    /**
     * @return Iterable by bills, contained in repository
     */
    @SneakyThrows
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }
}
