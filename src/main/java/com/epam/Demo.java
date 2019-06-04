package com.epam;

import com.epam.domain.Booking;
<<<<<<< HEAD
import com.epam.repo.BookingRepositoryImpl;
=======
import com.epam.domain.Hotel;
import com.epam.repo.BookingRepositoryImpl;
import com.epam.repo.HotelRepositoryImpl;
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2
import com.epam.utils.DBConnectionUtils;
import org.h2.store.fs.FileUtils;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        FileUtils.deleteRecursive("db", true);

        DBConnectionUtils.getConnection();

        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();

<<<<<<< HEAD
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
=======
        Booking booking = bookingRepository.findById((long) 1);
        System.out.println(booking);
        booking = bookingRepository.findById((long) 2);
        System.out.println(booking);
        booking = bookingRepository.findById((long) 3);
        System.out.println(booking);
        bookingRepository.removeById((long) 1);
        booking = bookingRepository.findById((long) 2);
        System.out.println(booking);
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2

    }
}
