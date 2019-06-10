package com.epam.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import lombok.SneakyThrows;

public class PrepStatement {

    @SneakyThrows
    public static PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

}
