package com.epam.repo;

import com.epam.domain.Bill;
import com.epam.domain.enums.BillStatus;
import com.epam.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import lombok.SneakyThrows;

public class BillRepoImpl implements Repository<Bill, Long> {

    public static final String INSERT_SQL_REQUEST = "INSERT INTO BILL ( USER_ID, BOOKING_ID, STATUS) VALUES( ?, ?, ?)";
    public static final String DELETE_SQL_REQUEST = "DELETE FROM BILL WHERE ID = ?";
    public static final String SELECT_FROM_SQL_REQUEST = "SELECT * FROM BILL WHERE ID = ?";
    public static final String UPDATE_SQL_REQUEST = " UPDATE BILL SET USER_ID = ?,  BOOKING_ID = ? , STATUS = ?, WHERE ID = ?";
    public static final String SELECT_ID_SQL_REQUEST = "SELECT ID FROM BILL";
    public static final String ID = "ID";
    public static final String BOOKING_ID = "BOOKING_ID";
    public static final String USER_ID = "USER_ID";
    public static final String STATUS = "STATUS";

    @SneakyThrows
    @Override
    public Bill save(Bill item) {
        if (item == null) {
            return null;
        } else {

            Long userId = item.getUserId();
            Long bookingId = item.getBookingId();
            BillStatus status = item.getStatus();

            PreparedStatement preparedStatement = getPreparedStatement(
                INSERT_SQL_REQUEST);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, bookingId);
            preparedStatement.setString(3, status.name());

            preparedStatement.executeUpdate();

        }

        return item;
    }

    @SneakyThrows
    @Override
    public Bill removeById(Long id) {
        Bill bill = null;

        if (id == null) {
            return null;
        } else {
            bill = findById(id);

            @Cleanup
            PreparedStatement preparedStatement = getPreparedStatement(
                DELETE_SQL_REQUEST);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }

        return bill;
    }

    @SneakyThrows
    @Override
    public Bill findById(Long id) {
        ResultSet resultSet = null;
        Bill bill = null;

        if (id == null) {
            return null;
        } else {

            PreparedStatement preparedStatement = getPreparedStatement(
                SELECT_FROM_SQL_REQUEST);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long tmpId = Long.parseLong(resultSet.getString(ID));
                if (tmpId.equals(id)) {

                    long bookingId = Long.parseLong(resultSet.getString(BOOKING_ID));
                    long userId = Long.parseLong(resultSet.getString(USER_ID));

                    String status = resultSet.getString(STATUS);

                    bill = Bill.builder().id(tmpId).bookingId(bookingId).userId(userId)
                        .status(Enum.valueOf(BillStatus.class, status)).build();

                }

            }
        }
        return bill;
    }

    @SneakyThrows
    @Override
    public Bill update(Bill item) {
        ResultSet resultSet = null;

        if (item == null) {
            return null;
        } else {

            Long id = item.getId();
            Long userId = item.getUserId();
            Long bookingId = item.getBookingId();
            BillStatus billStatus = item.getStatus();

            PreparedStatement preparedStatement = getPreparedStatement(
                UPDATE_SQL_REQUEST);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, bookingId);
            preparedStatement.setString(3, billStatus.name());
            preparedStatement.setLong(4, id);

            preparedStatement.execute();
        }

        return item;
    }

    @SneakyThrows
    @Override
    public Iterable<Bill> findAll() {
        List<Bill> billList = new ArrayList<>();
        Bill bill;

        @Cleanup
        PreparedStatement preparedStatement = getPreparedStatement(SELECT_ID_SQL_REQUEST);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet != null && resultSet.next()) {
            Long tmpId = resultSet.getLong(ID);
            billList.add(findById(tmpId));
        }

        return billList;

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
