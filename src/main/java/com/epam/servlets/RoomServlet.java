package com.epam.servlets;

import com.epam.domain.Bill;
import com.epam.domain.Room;
import com.epam.domain.enums.BillStatus;
import com.epam.domain.enums.RoomClass;
import com.epam.repo.Repository;
import com.epam.service.RoomServiceImpl;
import com.epam.state.RepositoryState;
import com.epam.state.ServiceState;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoomServlet extends BaseServlet  {
    private static RoomServiceImpl roomService = ServiceState.getRoomServiceInstance();

    private static Room getDemoRoom () {
        List<Bill> bills = new ArrayList<>();
        bills.add(Bill.builder()
                .id(1)
                .bookingId(1)
                .roomId(1)
                .status(BillStatus.PAID)
                .userId(1)
                .build());

        bills.add(Bill.builder()
                .id(2)
                .bookingId(2)
                .roomId(1)
                .status(BillStatus.PAID)
                .userId(1)
                .build());

        Room room = Room.builder()
                .id(1)
                .hotelId(1)
                .number(101)
                .numOfGuests(3)
                .pricePerNight(2000)
                .roomClass(RoomClass.STANDART)
                .bills(bills)
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
        Room room = roomService.findById(roomId);
//        Room room = getDemoRoom();
        log.debug("Send room: " + room);

        request.setAttribute("room", room);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/room_page.jsp");
        dispatcher.forward(request, response);
    }
}
