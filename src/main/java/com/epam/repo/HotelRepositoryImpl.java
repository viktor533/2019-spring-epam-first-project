package com.epam.repo;

import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.utils.DBConnectionUtils;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class HotelRepositoryImpl implements Repository<Hotel, Long> {
    Connection connection = DBConnectionUtils.getConnection();

    @Override
    public Hotel save(Hotel item) {
        return null;
    }

    @Override
    public Hotel removeById(Long id) {
        return null;
    }

    @Override
    @SneakyThrows
    public Hotel findById(Long id) {
        String sql = "SELECT * FROM HOTEL WHERE ID = ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql + id);

        String name = null;
        String location = null;
        int luxury = 0;

        while (resultSet.next()) {
            name = resultSet.getString("NAME");
            location = resultSet.getNString("LOCATION");
            luxury = resultSet.getInt("LUXURY");
        }
        List<Room> rooms = null;
        Hotel.builder();
        Hotel hotel = Hotel.builder().build();

        hotel.setId(id);
        hotel.setName(name);
        hotel.setLocation(location);
        hotel.setLuxury(luxury);
        hotel.setRooms(rooms);

        return hotel;
    }

    @Override
    public Hotel update(Hotel item) {
        return null;
    }

    @Override
    public Iterable<Hotel> findAll() {
        return null;
    }
}
