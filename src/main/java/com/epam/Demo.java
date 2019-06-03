package com.epam;

import com.epam.domain.Hotel;
import com.epam.repo.HotelRepositoryImpl;
import com.epam.utils.DBConnectionUtils;

import java.io.File;

import static com.epam.utils.IOUtils.deleteFolder;

public class Demo {
    public static void main(String[] args) {
        deleteFolder(new File("/home/alexey/IdeaProjects/2019-spring-epam-first-project/db"));

        DBConnectionUtils.getConnection();

        HotelRepositoryImpl hotelRepository = new HotelRepositoryImpl();
        Hotel hotel = hotelRepository.findById((long) 1);
        System.out.println(hotel.getName());
        hotelRepository.removeById((long) 1);
        hotel = hotelRepository.findById((long) 1);
        System.out.println(hotel.getName());
    }
}
