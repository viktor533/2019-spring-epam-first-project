package com.epam;

import com.epam.domain.Hotel;
import com.epam.repo.HotelRepositoryImpl;
import com.epam.utils.DBConnectionUtils;

public class Demo {
    public static void main(String[] args) {
        DBConnectionUtils.getConnection();

        HotelRepositoryImpl hotelRepository = new HotelRepositoryImpl();
        Hotel hotel = hotelRepository.findById((long)1);
        System.out.println(hotel.getLocation());
    }
}
