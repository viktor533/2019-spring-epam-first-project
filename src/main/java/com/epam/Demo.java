package com.epam;

import com.epam.domain.Booking;
import com.epam.domain.Hotel;
import com.epam.repo.BookingRepositoryImpl;
import com.epam.repo.HotelRepositoryImpl;
import com.epam.utils.DBConnectionUtils;
import org.h2.store.fs.FileUtils;

import java.io.File;
import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        FileUtils.deleteRecursive("db", true);

        DBConnectionUtils.getConnection();

        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();

        Booking booking = bookingRepository.findById((long) 1);
        System.out.println(booking);
        booking = bookingRepository.findById((long) 2);
        System.out.println(booking);
        booking = bookingRepository.findById((long) 3);
        System.out.println(booking);
        bookingRepository.removeById((long) 1);
        booking = bookingRepository.findById((long) 2);
        System.out.println(booking);

    }
}
