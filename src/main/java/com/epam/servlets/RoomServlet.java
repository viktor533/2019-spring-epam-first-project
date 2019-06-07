package com.epam.servlets;

import com.epam.domain.Room;
import com.epam.service.RoomService;
import com.epam.state.ServiceState;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RoomServlet extends BaseServlet  {
    private static RoomService roomService = ServiceState.getRoomServiceInstance();

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
