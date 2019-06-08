package com.epam.repo;

import com.epam.domain.Bill;
import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.state.RepositoryState;
import com.epam.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserRepoImpl implements Repository<User, Long> {


    private static final String INSERT_SQL_REQUEST = "INSERT INTO USER (LOGIN, PASSWORD, ROLE) VALUES(?, ?, ?)";
    private static final String DELETE_SQL_REQUEST = "DELETE FROM USER WHERE ID = ?";
    private static final String SELECT_FROM_USER_SQL_REQUEST = "SELECT * FROM USER WHERE ID = ?";
    private static final String UPDATE_SQL_REQUEST = " UPDATE USER SET LOGIN = ?,  PASSWORD = ? , ROLE = ? WHERE ID = ?";
    private static final String SELECT_ID_SQL_REQUEST = "SELECT ID FROM USER";

    private Repository<Bill, Long> billRepository = RepositoryState.getBillRepositoryInstance();

    @SneakyThrows
    @Override
    public User save(User item) {
        if (item == null) {
            log.error("item is null");
            throw new IllegalArgumentException();
        } else {
            String login = item.getLogin();
            String password = item.getPassword();
            UserRole role = item.getRole();

            PreparedStatement preparedStatement = getPreparedStatement(
                INSERT_SQL_REQUEST);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role.name());


        }

        return item;
    }


    @SneakyThrows
    @Override
    public User removeById(Long id) {
        User user;

        if (id == null) {
            log.error("id is null");
            throw new IllegalArgumentException();
        } else {
            user = findById(id);

            @Cleanup
            PreparedStatement preparedStatement = getPreparedStatement(
                DELETE_SQL_REQUEST);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }

        return user;
    }

    @SneakyThrows
    @Override
    public User findById(Long id) {
        User user = null;

        if (id == null) {
            log.error("id is null");
            throw new IllegalArgumentException();
        } else {

            PreparedStatement preparedStatement = getPreparedStatement(
                SELECT_FROM_USER_SQL_REQUEST);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Long tmpId = Long.parseLong(resultSet.getString("ID"));

                    if (tmpId.equals(id)) {

                        String login = resultSet.getString("LOGIN");
                        String password = resultSet.getString("PASSWORD");
                        String role = resultSet.getString("ROLE");
                        UserRole userRole = Enum.valueOf(UserRole.class, role);

                        user = User.builder().id(tmpId).login(login).password(password)
                            .role(userRole)
                            .build();

                    }
                }
            }
        }
        if (user != null) {
            addBillsToUser(user);
        }

        return user;

    }
    @SneakyThrows
    @Override
    public User update(User item) {

        if (item == null) {
            log.error("item is null");
            throw new IllegalArgumentException();
        } else {

            Long id = item.getId();
            String login = item.getLogin();
            String password = item.getPassword();
            UserRole role = item.getRole();

            PreparedStatement preparedStatement = getPreparedStatement(
                UPDATE_SQL_REQUEST);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role.name());
            preparedStatement.setLong(4, id);

            preparedStatement.execute();
        }

        return item;

    }

    @SneakyThrows
    @Override
    public Iterable<User> findAll() {
        List<User> userList = new ArrayList<>();

        @Cleanup
        PreparedStatement preparedStatement = getPreparedStatement(SELECT_ID_SQL_REQUEST);
        preparedStatement.execute();
        try (ResultSet resultSet = preparedStatement.getResultSet()) {

            while (resultSet.next()) {
                Long tmpId = resultSet.getLong("ID");
                userList.add(findById(tmpId));
            }
        }

        return userList;
    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

    private void addBillsToUser(User user) {
        List<Bill> bills;
        Iterable<Bill> allBookings =  billRepository.findAll();
        if (allBookings != null) {
            bills = new ArrayList<>();
            for (Bill bill : allBookings) {
                if (bill.getRoomId() == user.getId()) {
                    bills.add(bill);
                }
            }
        } else {
            bills = Collections.emptyList();
        }
        user.setBills(bills);
    }
}
