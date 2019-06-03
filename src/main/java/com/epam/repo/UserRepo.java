package com.epam.repo;

import com.epam.domain.Bill;
import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserRepo implements Repository<User, Long> {

    Statement statement = null;
    String sql = "SELECT * FROM USER WHERE ID = ";

    {
        Connection connection = DBConnectionUtils.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public User save(User item) {
        return null;
    }

    @Override
    public User removeById(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        ResultSet resultSet = null;
        User user = User.builder().build();

        try {
            resultSet = statement.executeQuery(sql + id);
            while (resultSet.next()) {
                Long tmpId = Long.parseLong(resultSet.getString("ID"));

                if (tmpId.equals(id)) {
                    user.setId(tmpId);
                    user.setLogin(resultSet.getString("LOGIN"));
                    user.setPassword(resultSet.getString("PASSWORD"));

                    String role = resultSet.getString("ROLE");
                    user.setRole(Enum.valueOf(UserRole.class, role));

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

        String update = "UPDATE USER SET LOGIN = \'" + login + "\' , PASSWORD = \'" + pass + "\' , ROLE = \'" + strRole + "\' WHERE ID = " + id + ";";
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


}
