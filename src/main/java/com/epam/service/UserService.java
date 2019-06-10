package com.epam.service;

import com.epam.domain.User;
import com.epam.repo.Repository;
import com.epam.utils.PrepStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

    private final Repository<User, Long> userRepo;

    public UserService(Repository<User, Long> userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Accept user, check by null and save it in repository
     *
     * @param user that we need to save
     * @return saved user
     */
    @SneakyThrows
    public User save(User user) {
        return userRepo.save(user);
    }

    /**
     * Accepted id and try to delete user with same id
     *
     * @param id - param the we need to remove by
     * @return removed user or null, if user with same id does not exist
     */
    @SneakyThrows
    public User removeById(Long id) {
        log.debug("removeById User");
        return userRepo.removeById(id);
    }

    /**
     * Accept id and try to find user with same id
     *
     * @param id - param the we need to remove by
     * @return found user or null, if user with same id does not exist
     */
    @SneakyThrows
    public User findById(Long id) {
        return userRepo.findById(id);
    }

    @SneakyThrows
    public User findByLogin(String login) throws IllegalArgumentException {
        Iterable<User> allUsers = userRepo.findAll();
        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }


    /**
     * Update user
     *
     * @param user - that we need to update
     * @return updated user or null if same user not exist in repository
     */
    @SneakyThrows
    public User update(User user) {
        return userRepo.update(user);
    }

    /**
     * @return Iterable by bills, contained in repository
     */
    @SneakyThrows
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @SneakyThrows
    public boolean checkIfAlreadyExistsInDb(String email) {
        boolean isUnique = false;

        PreparedStatement preparedStatement = PrepStatement
            .getPreparedStatement("SELECT LOGIN FROM USER");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String loginInDB = resultSet.getString("LOGIN");

            if (loginInDB.equals(email)) {
                isUnique = true;
                System.out.println("THIS USERNAME IS TAKEN");
            }
        }

        return isUnique;
    }
}
