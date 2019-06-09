package com.epam.repo;

import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.state.RepositoryState;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotelRepositoryImpl implements Repository<Hotel, Long> {
    private Repository <Room, Long> roomRepository = RepositoryState.getRoomRepositoryInstance();

    private static final String NAME_COLUMN_NAME = "NAME";
    private static final String LOCATION_DATE_COLUMN_NAME = "LOCATION";
    private static final String LUXURY_COLUMN_NAME = "LUXURY";

    private static final String SAVE_EXCEPTION_MESSAGE = "Passing empty hotel field to save";
    private static final String REMOVE_EXCEPTION_MESSAGE = "Passing empty id field to remove";
    private static final String FIND_EXCEPTION_MESSAGE = "Passing empty id field to find";
    private static final String UPDATE_EXCEPTION_MESSAGE = "Passing empty hotel field to update";

    @Override
    @SneakyThrows
    public Hotel save(Hotel hotel) {
        if (hotel == null) {
            log.error("hotel is null");
            throw new IllegalArgumentException(SAVE_EXCEPTION_MESSAGE);
        } else {
            String saveSqlRequest = "INSERT INTO HOTEL (NAME, LOCATION, LUXURY) VALUES (?, ?, ?)";
            @Cleanup
            PreparedStatement statement = getPreparedStatement(saveSqlRequest);
            List<Room> roomList = hotel.getRooms();
            for (Room room : roomList) {
                roomRepository.save(room);
            }

            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setInt(3, hotel.getLuxury());
            statement.executeUpdate();

            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Hotel removeById(Long id) {
        if (id == null) {
            log.error("id is null");
            throw new IllegalArgumentException(REMOVE_EXCEPTION_MESSAGE);
        } else {
            Hotel hotel = findById(id);

            String deleteSqlRequest = "DELETE FROM HOTEL WHERE ID = ?";
            @Cleanup
            PreparedStatement statement = getPreparedStatement(deleteSqlRequest);
            statement.setLong(1, id);
            statement.executeUpdate();

            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Hotel findById(Long id) {
        if (id == null) {
            log.error("id is null");
            throw new IllegalArgumentException(FIND_EXCEPTION_MESSAGE);
        } else {
            Hotel hotel = null;

            String findSqlRequest = "SELECT * FROM HOTEL WHERE ID = ?";
            @Cleanup
            PreparedStatement statement = getPreparedStatement(findSqlRequest);
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String name = resultSet.getString(NAME_COLUMN_NAME);
                    String location = resultSet.getString(LOCATION_DATE_COLUMN_NAME);
                    int luxury = resultSet.getInt(LUXURY_COLUMN_NAME);
                    hotel = Hotel.builder().id(id).name(name).location(location).luxury(luxury)
                        .build();
                    addRoomsToHotel(hotel);
                }
            }


            if (hotel != null) {
                addRoomsToHotel(hotel);
            }
            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Hotel update(Hotel hotel) {
        if (hotel == null) {
            log.error("hotel is null");
            throw new IllegalArgumentException(UPDATE_EXCEPTION_MESSAGE);
        } else {
            String updateSqlRequest = "UPDATE HOTEL SET NAME = ?, LOCATION = ?, LUXURY = ? WHERE ID = ?";
            @Cleanup
            PreparedStatement statement = getPreparedStatement(updateSqlRequest);

            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setInt(3, hotel.getLuxury());
            statement.setLong(4, hotel.getId());
            statement.executeUpdate();

            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Iterable<Hotel> findAll() {
        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel;

        String findAllSqlRequest = "SELECT * FROM HOTEL";
        @Cleanup
        PreparedStatement statement = getPreparedStatement(findAllSqlRequest);
        try (ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String idColumnName = "ID";
                hotel = Hotel.builder().id(resultSet.getLong(idColumnName))
                    .name(resultSet.getString(NAME_COLUMN_NAME))
                    .location(resultSet.getString(LOCATION_DATE_COLUMN_NAME))
                    .luxury(resultSet.getInt(LUXURY_COLUMN_NAME))
                    .build();
                addRoomsToHotel(hotel);
                hotelList.add(hotel);
            }
        }

        return hotelList;
    }

    private void addRoomsToHotel(Hotel hotel) {
        List<Room> roomList;
        Iterable<Room> allRooms =  roomRepository.findAll();
        if (allRooms != null) {
            roomList = new ArrayList<>();
            for (Room room : allRooms) {
                if (room.getHotelId() == hotel.getId()) {
                    roomList.add(room);
                }
            }
        } else {
            roomList = Collections.emptyList();
        }

        hotel.setRooms(roomList);
    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }
}
