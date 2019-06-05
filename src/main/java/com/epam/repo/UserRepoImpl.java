package com.epam.repo;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import lombok.SneakyThrows;


public class UserRepoImpl implements Repository<User, Long> {


    public static final String INSERT_SQL_REQUEST = "INSERT INTO USER (LOGIN, PASSWORD, ROLE) VALUES(?, ?, ?)";
    public static final String DELETE_SQL_REQUEST = "DELETE FROM USER WHERE ID = ?";
    public static final String SELECT_FROM_USER_SQL_REQUEST = "SELECT * FROM USER WHERE ID = ?";
    public static final String UPDATE_SQL_REQUEST = " UPDATE USER SET LOGIN = ?,  PASSWORD = ? , ROLE = ? WHERE ID = ?";
    public static final String SELECT_ID_SQL_REQUEST = "SELECT ID FROM USER";

    @SneakyThrows
    @Override
    public User save(User item) throws IllegalArgumentException{
        if (item == null) {
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
    public User removeById(Long id) throws IllegalArgumentException {
        User user = null;

        if (id == null) {
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
    public User findById(Long id) throws IllegalArgumentException{
        ResultSet resultSet = null;
        User user = null;

        if (id == null) {
            throw new IllegalArgumentException();
        } else {

            PreparedStatement preparedStatement = getPreparedStatement(
                SELECT_FROM_USER_SQL_REQUEST);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long tmpId = Long.parseLong(resultSet.getString("ID"));

                if (tmpId.equals(id)) {

                    String login = resultSet.getString("LOGIN");
                    String password = resultSet.getString("PASSWORD");
                    String role = resultSet.getString("ROLE");
                    UserRole userRole = Enum.valueOf(UserRole.class, role);

                    user = User.builder().id(tmpId).login(login).password(password).role(userRole)
                        .build();

                }
            }
        }

        return user;

    }
    @SneakyThrows
    @Override
    public User update(User item) throws IllegalArgumentException {
        ResultSet resultSet = null;

        if (item == null) {
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
        User user;

        @Cleanup
        PreparedStatement preparedStatement = getPreparedStatement(SELECT_ID_SQL_REQUEST);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet != null && resultSet.next()) {
            Long tmpId = resultSet.getLong("ID");
            userList.add(findById(tmpId));
        }

        return userList;
    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

    @SneakyThrows
    private Statement getStatement() {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.createStatement();
    }



}
