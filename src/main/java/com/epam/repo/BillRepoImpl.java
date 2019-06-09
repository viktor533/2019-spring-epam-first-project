package com.epam.repo;

import com.epam.domain.Bill;
import com.epam.domain.enums.BillStatus;
import com.epam.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BillRepoImpl implements Repository<Bill, Long> {

    private static final String INSERT_SQL_REQUEST = "INSERT INTO BILL ( USER_ID, ROOM_ID, BOOKING_ID, STATUS) VALUES( ?, ?, ?, ?)";
    private static final String DELETE_SQL_REQUEST = "DELETE FROM BILL WHERE ID = ?";
    private static final String SELECT_FROM_SQL_REQUEST = "SELECT * FROM BILL WHERE ID = ?";
    private static final String UPDATE_SQL_REQUEST = " UPDATE BILL SET USER_ID = ?,  ROOM_ID = ?, BOOKING_ID = ? , STATUS = ?, WHERE ID = ?";
    private static final String SELECT_ID_SQL_REQUEST = "SELECT ID FROM BILL";

    public static final String ID = "ID";
    private static final String BOOKING_ID = "BOOKING_ID";
    private static final String USER_ID = "USER_ID";
    private static final String STATUS = "STATUS";
    private static final String ROOM_ID = "ROOM_ID";

    @SneakyThrows
    @Override
    public Bill save(Bill item) {
        if (item == null) {
            log.error("item is null");
            throw new IllegalArgumentException();
        } else {

            Long userId = item.getUserId();
            Long roomId = item.getRoomId();
            Long bookingId = item.getBookingId();
            BillStatus status = item.getStatus();

            PreparedStatement preparedStatement = getPreparedStatement(
                INSERT_SQL_REQUEST);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, roomId);
            preparedStatement.setLong(3, bookingId);
            preparedStatement.setString(4, status.name());

            preparedStatement.executeUpdate();

        }

        return item;
    }

    @SneakyThrows
    @Override
    public Bill removeById(Long id){
        Bill bill;

        if (id == null) {
            log.error("id is null");
            throw new IllegalArgumentException();
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
        Bill bill = null;

        if (id == null) {
            log.error("id is null");
            throw new IllegalArgumentException();
        } else {

            PreparedStatement preparedStatement = getPreparedStatement(
                SELECT_FROM_SQL_REQUEST);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()) {
                    Long tmpId = Long.parseLong(resultSet.getString(ID));
                    if (tmpId.equals(id)) {

                        long bookingId = Long.parseLong(resultSet.getString(BOOKING_ID));
                        long userId = Long.parseLong(resultSet.getString(USER_ID));
                        long roomId = Long.parseLong(resultSet.getString(ROOM_ID));

                        String status = resultSet.getString(STATUS);

                        bill = Bill.builder().id(tmpId).bookingId(bookingId).roomId(roomId)
                            .userId(userId).status(Enum.valueOf(BillStatus.class, status)).build();

                    }

                }
            }
        }
        return bill;
    }

    @SneakyThrows
    @Override
    public Bill update(Bill item) {

        if (item == null) {
            log.error("item is null");
            throw new IllegalArgumentException();
        } else {

            Long id = item.getId();
            Long userId = item.getUserId();
            Long roomId = item.getRoomId();
            Long bookingId = item.getBookingId();
            BillStatus billStatus = item.getStatus();

            PreparedStatement preparedStatement = getPreparedStatement(
                UPDATE_SQL_REQUEST);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, roomId);
            preparedStatement.setLong(3, bookingId);
            preparedStatement.setString(4, billStatus.name());
            preparedStatement.setLong(5, id);

            preparedStatement.execute();
        }

        return item;
    }

    @SneakyThrows
    @Override
    public Iterable<Bill> findAll() {
        List<Bill> billList = new ArrayList<>();

        @Cleanup
        PreparedStatement preparedStatement = getPreparedStatement(SELECT_ID_SQL_REQUEST);
        preparedStatement.execute();
        try (ResultSet resultSet = preparedStatement.getResultSet()) {

            while (resultSet.next()) {
                Long tmpId = resultSet.getLong(ID);
                billList.add(findById(tmpId));
            }
        }

        return billList;

    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

}
