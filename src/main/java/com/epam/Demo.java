package com.epam;

import com.epam.domain.Booking;
import com.epam.domain.enums.RoomClass;
import com.epam.service.*;
import com.epam.state.ServiceState;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        BookingService bookingServiceInstance = ServiceState.getBookingServiceInstance();
        HotelService hotelServiceInstance = ServiceState.getHotelServiceInstance();
        RoomService roomServiceInstance = ServiceState.getRoomServiceInstance();
        BillService billServiceInstance = ServiceState.getBillServiceInstance();
        UserService userServiceInstance = ServiceState.getUserServiceInstance();
        System.out.println("----------------bookingService-----------------");
        Iterable<Booking> bookings = bookingServiceInstance.findAll();
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
        Booking bookingRemoved = bookingServiceInstance.removeById(3L);
        System.out.println("---------------------------------");
        System.out.println("removed " + bookingRemoved);
        System.out.println("---------------------------------");
        bookings = bookingServiceInstance.findAll();
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
        System.out.println("---------------------------------");
        Booking bookingAdded = bookingServiceInstance.save(Booking.builder().start(LocalDate.now()).end(LocalDate.now()).roomClass(RoomClass.STANDART).build());
        System.out.println("added " + bookingAdded);
        System.out.println("---------------------------------");
        bookings = bookingServiceInstance.findAll();
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
        System.out.println("---------------------------------");
        Booking update = bookingServiceInstance.update(Booking.builder().id(1L).start(LocalDate.now()).end(LocalDate.now()).roomClass(RoomClass.STANDART).build());
        System.out.println("updated " + update);
        System.out.println("---------------------------------");
        bookings = bookingServiceInstance.findAll();
        for (Booking booking : bookings) {
            System.out.println(booking);
        }

    }
}
