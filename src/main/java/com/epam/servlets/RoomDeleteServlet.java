package com.epam.servlets;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.RoomService;
import com.epam.state.ServiceState;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RoomDeleteServlet extends BaseServlet  {

    private static RoomService roomService = ServiceState.getRoomServiceInstance();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdStr = request.getParameter("roomId");
        Long roomId = null;
        if (roomIdStr != null) {
            roomId = Long.parseLong(roomIdStr);
        }
        log.debug("Accept roomId: " + roomId);
        User user = LoginServlet.checkToken(request);
        if (user == null || !UserRole.ADMIN.equals(user.getRole())) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bad_session_page.jsp");
            dispatcher.forward(request, response);

        }
        roomService.removeById(roomId);
        response.sendRedirect(request.getContextPath() + "/hotel");
    }
}
