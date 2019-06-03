package com.epam;

import com.epam.domain.Booking;
import com.epam.repo.BookingRepositoryImpl;
import com.epam.utils.DBConnectionUtils;
import org.h2.store.fs.FileUtils;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        FileUtils.deleteRecursive("db", true);

        DBConnectionUtils.getConnection();

        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();

        Booking booking;

        for (Booking b : bookingRepository.findAll()) {
            System.out.println(b);
        }

        bookingRepository.save(Booking.builder().roomId(2).start(LocalDate.now()). end(LocalDate.now()).build());
        booking = bookingRepository.findById((long) 4);

        System.out.println(booking);
        bookingRepository.update(Booking.builder().id(2).roomId(2).start(LocalDate.now()). end(LocalDate.now()).build());
        booking = bookingRepository.findById((long) 2);
        System.out.println(booking);
        for (Booking b : bookingRepository.findAll()) {
            System.out.println(b);
        }

    }
}
