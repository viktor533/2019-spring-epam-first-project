package com.epam.utils;

import java.io.FileReader;
import java.util.Objects;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

import org.h2.store.fs.FileUtils;
import org.h2.tools.RunScript;

@Slf4j
public class DBConnectionUtils {

    private static DBConnectionUtils instance;
    private static Connection connection;

    @SneakyThrows
    private DBConnectionUtils() {
        FileUtils.deleteRecursive("db", true);
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:./db/test", "sa", "");
        log.debug("connection with name " + connection.toString() + " was established");
        RunScript.execute(connection, new FileReader(Objects
            .requireNonNull(getClass().getClassLoader().getResource("db/schema.sql")).getFile()));
        log.debug("init schema was created");
        RunScript.execute(connection, new FileReader(Objects
            .requireNonNull(getClass().getClassLoader().getResource("db/test-data.sql")).getFile()));
        log.debug("test data were added");
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new DBConnectionUtils();
        }
        return connection;
    }
}
