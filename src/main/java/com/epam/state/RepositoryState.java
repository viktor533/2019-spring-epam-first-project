package com.epam.state;

import com.epam.domain.*;
import com.epam.repo.*;

public class RepositoryState {
    private static Repository<Booking, Long> bookingRepository = null;
    private static Repository<Bill, Long> billRepository = null;
    private static Repository<Hotel, Long> hotelRepository = null;
    private static Repository<Room, Long> roomRepository = null;
    private static Repository<User, Long> userRepository = null;

    private RepositoryState() {}

    public static Repository<Booking, Long> getBookingRepositoryInstance() {
        if (bookingRepository == null) {
            bookingRepository = new BookingRepositoryImpl();
        }
        return bookingRepository;
    }

    public static Repository<Bill, Long> getBillRepositoryInstance() {
        if (billRepository == null) {
            billRepository = new BillRepoImpl();
        }
        return billRepository;
    }

    public static Repository<Hotel, Long> getHotelRepositoryInstance() {
        if (hotelRepository == null) {
            hotelRepository = new HotelRepositoryImpl();
        }
        return hotelRepository;
    }

    public static Repository<Room, Long> getRoomRepositoryInstance() {
        if (roomRepository == null) {
            roomRepository = new RoomRepositoryImpl();
        }
        return roomRepository;
    }

    public static Repository<User, Long> getUserRepositoryInstance() {
        if (userRepository == null) {
            userRepository = new UserRepoImpl();
        }
        return userRepository;
    }
}
