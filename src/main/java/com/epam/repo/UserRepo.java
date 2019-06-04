package com.epam.repo;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.SneakyThrows;


public class UserRepo implements Repository<User, Long> {

    Statement statement = null;
    String sql = null;

    public UserRepo(String sql) {
        this.sql = sql;
    }

    public UserRepo() {
        sql = "SELECT * FROM USER WHERE ID = "; // default sql version
    }

    @SneakyThrows
    @Override
    public User save(User item) {
        if (item == null) {
            return null;
        } else {
            String login = item.getLogin();
            String password = item.getPassword();
            UserRole role = item.getRole();

            PreparedStatement preparedStatement = getPreparedStatement("INSERT INTO USER (LOGIN, PASSWORD, ROLE) VALUES(?, ?, ?)");

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role.name());


        }

        return item;
    }

    @Override
    public User removeById(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        ResultSet resultSet = null;
        User user = null;

        try {
            resultSet = statement.executeQuery(sql + id);
            while (resultSet.next()) {
                Long tmpId = Long.parseLong(resultSet.getString("ID"));

                if (tmpId.equals(id)) {
                    String login = resultSet.getString("LOGIN");
                    String password = resultSet.getString("PASSWORD");

                    String role = resultSet.getString("ROLE");

                    user = User.builder().id(tmpId).login(login).password(password)
                        .role(Enum.valueOf(UserRole.class, role)).build();

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;

    }

    @Override
    public User update(User item) {
        ResultSet resultSet;
        User user;

        Long id = item.getId();
        String login = item.getLogin();
        String pass = item.getPassword();
        UserRole role = item.getRole();
        String strRole = role.name();

        String update =
            "UPDATE USER SET LOGIN = '" + login + "' , PASSWORD = '" + pass + "' , ROLE = '"
                + strRole + "' WHERE ID = " + id;
        try {
            statement.execute(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        user = findById(id);

        return user;

    }

    @Override
    public Iterable<User> findAll() {
        return null;
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
