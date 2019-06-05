package com.epam.servlets;

import com.epam.domain.Room;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomServlet extends BaseServlet  {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdStr = request.getParameter("roomId");
        int roomId = -1;
        if (roomIdStr != null) {
            roomId = Integer.parseInt(roomIdStr);
        }
        System.err.println("RoomId: " + roomId);
//        Room room = RoomService.get(roomId);
        Room room = null;
        request.setAttribute("room", room);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/room_page.jsp");
        dispatcher.forward(request, response);
    }

}
