package com.epam.servlets;

import com.epam.domain.Booking;
import com.epam.domain.Room;
import com.epam.domain.enums.RoomClass;
import com.epam.repo.Repository;
import com.epam.state.RepositoryState;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoomServlet extends BaseServlet  {
    private static Repository<Room, Long> roomService = RepositoryState.getRoomRepositoryInstance();

    private static Room getDemoRoom () {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(Booking.builder()
                .id(1)
                .roomId(1)
                .start(LocalDate.now())
                .end(LocalDate.now().plusDays(3))
                .build());

        bookings.add(Booking.builder()
                .id(2)
                .roomId(1)
                .start(LocalDate.now())
                .end(LocalDate.now().plusDays(5))
                .build());

        Room room = Room.builder()
                .id(1)
                .hotelId(1)
                .numOfGuests(3)
                .pricePerNight(2000)
                .roomClass(RoomClass.STANDART)
                .bookings(bookings)
                .build();

        return room;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdStr = request.getParameter("roomId");
        Long roomId = null;
        if (roomIdStr != null) {
            roomId = Long.parseLong(roomIdStr);
        }
        log.debug("Accept roomId: " + roomId);
//        Room room = roomService.findById(roomId);
        Room room = getDemoRoom();
        log.debug("Send room: " + room);

        request.setAttribute("room", room);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/room_page.jsp");
        dispatcher.forward(request, response);
    }
}
