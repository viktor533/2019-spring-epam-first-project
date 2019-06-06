package com.epam.state;

import com.epam.service.*;

public class ServiceState {
    private static BookingService bookingService = null;
    private static BillService billService = null;
    private static HotelService hotelService = null;
    private static RoomServiceImpl roomService = null;
    private static UserService userService = null;

    public static BookingService getBookingServiceInstance() {
        if (bookingService == null) {
            bookingService = new BookingService(RepositoryState.getBookingRepositoryInstance());
        }
        return bookingService;
    }

    public static BillService getBillServiceInstance() {
        if (billService == null) {
            billService = new BillService(RepositoryState.getBillRepositoryInstance());
        }
        return billService;
    }

    public static HotelService getHotelServiceInstance() {
        if (hotelService == null) {
            hotelService = new HotelService(RepositoryState.getHotelRepositoryInstance());
        }
        return hotelService;
    }

    public static RoomServiceImpl getRoomServiceInstance() {
        if (roomService == null) {
            roomService = new RoomServiceImpl();
        }
        return roomService;
    }

    public static UserService getUserServiceInstance() {
        if (userService == null) {
            userService = new UserService(RepositoryState.getUserRepositoryInstance());
        }
        return userService;
    }
}
