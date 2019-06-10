package com.epam.repo;

import com.epam.domain.Token;
import com.epam.domain.enums.UserRole;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TokenRepositoryImpl implements Repository<Token, String> {

    private static final String SAVE_SQL_REQUEST = "INSERT INTO TOKEN_TABLE (TOKEN, USER_ID, ROLE) VALUES(?, ?, ?);";
    private static final String DELETE_SQL_REQUEST = "DELETE FROM TOKEN_TABLE WHERE TOKEN = ?;";
    private static final String FIND_SQL_REQUEST = "SELECT * FROM TOKEN_TABLE WHERE TOKEN = ?;";
    private static final String UPDATE_SQL_REQUEST = "UPDATE TOKEN_TABLE SET TOKEN \'?\', USER_ID \'?\', ROLE = \'?\' WHERE TOKEN = ?;";
    private static final String FIND_ALL_SQL_REQUEST = "SELECT * FROM TOKEN_TABLE;";

    private static final String TOKEN_COLUMN_NAME = "TOKEN";
    private static final String USER_ID_COLUMN_NAME = "USER_ID";
    private static final String ROLE_COLUMN_NAME = "ROLE";

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

    @SneakyThrows
    private void setTokenToPreparedStatement(Token token, PreparedStatement statement) {
        statement.setString(1, token.getToken());
        statement.setLong(2, token.getUserId());
        statement.setString(3, token.getRole().toString());
    }

    @Override
    @SneakyThrows
    public Token save(Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Accepted room is null!");
        }
        @Cleanup
        PreparedStatement statement = getPreparedStatement(SAVE_SQL_REQUEST);
        setTokenToPreparedStatement(token, statement);
        statement.execute();
        return token;
    }

    @Override
    @SneakyThrows
    public Token removeById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }
        Token token = findById(id);
        if (token == null) {
            return null;
        }

        @Cleanup
        PreparedStatement statement = getPreparedStatement(DELETE_SQL_REQUEST);
        statement.setString(1, id);
        statement.execute();

        return token;
    }

    @Override
    @SneakyThrows
    public Token findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }

        Token token = null;

        @Cleanup
        PreparedStatement statement = getPreparedStatement(FIND_SQL_REQUEST);

        try {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                token = Token.builder()
                        .token(resultSet.getString(TOKEN_COLUMN_NAME))
                        .userId(resultSet.getLong(USER_ID_COLUMN_NAME))
                        .role(UserRole.valueOf(resultSet.getString(ROLE_COLUMN_NAME)))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }

        return token;
    }

    @Override
    @SneakyThrows
    public Token update(Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Accepted room is null!");
        }
        @Cleanup
        PreparedStatement statement = getPreparedStatement(UPDATE_SQL_REQUEST);
        setTokenToPreparedStatement(token, statement);
        statement.setString(4, token.getToken());
        statement.execute();

        return token;
    }

    @Override
    @SneakyThrows
    public Iterable<Token> findAll() {
        @Cleanup
        PreparedStatement statement = getPreparedStatement(FIND_ALL_SQL_REQUEST);
        ResultSet resultSet = statement.executeQuery();
        List<Token> tokensList = new ArrayList<>();
        while (resultSet.next()) {
            Token token = Token.builder()
                    .token(resultSet.getString(TOKEN_COLUMN_NAME))
                    .userId(resultSet.getLong(USER_ID_COLUMN_NAME))
                    .role(UserRole.valueOf(resultSet.getString(ROLE_COLUMN_NAME)))
                    .build();
            tokensList.add(token);
        }
        return tokensList;
    }
}
