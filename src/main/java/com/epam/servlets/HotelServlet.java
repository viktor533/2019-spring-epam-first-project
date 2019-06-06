package com.epam.servlets;


import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.domain.enums.RoomClass;
import com.epam.service.HotelService;
import com.epam.state.ServiceState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;

public class HotelServlet extends BaseServlet {
    HotelService hotelService = ServiceState.getHotelServiceInstance();

    private static Hotel getDemoHotel() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(Room.builder()
                .id(1)
                .hotelId(1)
                .number(101)
                .numOfGuests(3)
                .pricePerNight(2000)
                .roomClass(RoomClass.STANDART)
                .build());
        rooms.add(Room.builder()
                .id(2)
                .hotelId(1)
                .number(102)
                .numOfGuests(1)
                .pricePerNight(5000)
                .roomClass(RoomClass.LUX)
                .build());

        Hotel hotel = Hotel.builder()
                .id(1)
                .name("Reddison")
                .location("SPb")
                .luxury(5)
                .rooms(rooms)
                .build();
        return hotel;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hotel hotel = hotelService.findById(1L);
        request.setAttribute("hotel", hotel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/hotel_page.jsp");
        dispatcher.forward(request, response);
    }

}
