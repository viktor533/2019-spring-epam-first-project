package com.epam.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnectionUtils {
    private static DBConnectionUtils instance;
    private static Connection connection;
    static final String JDBC_DRIVER = "org.h2.Driver";

    private DBConnectionUtils(String dbUrl, String user, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            log.info("some error with JDBC");
        } catch (ClassNotFoundException e) {
            log.info("some error with Class.forName");
        }
    }

    public static Connection getConnection(String dbUrl, String user, String password) {
        if (instance == null) {
            synchronized (DBConnectionUtils.class) {
                if (instance == null) {
                    instance = new DBConnectionUtils(dbUrl, user, password);
                    log.info("DBConnection was instantiated.");
                }
            }
        }

        return instance.connection;
    }
}
