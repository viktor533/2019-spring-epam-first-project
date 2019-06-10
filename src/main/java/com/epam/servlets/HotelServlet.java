package com.epam.servlets;


import com.epam.domain.Hotel;
import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.HotelService;
import com.epam.state.ServiceState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.*;

public class HotelServlet extends BaseServlet {
    private HotelService hotelService = ServiceState.getHotelServiceInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = LoginServlet.checkToken(request);
        if (user == null || !UserRole.ADMIN.equals(user.getRole())) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bad_session_page.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Hotel hotel = hotelService.findById(1L);
        request.setAttribute("hotel", hotel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/hotel_page.jsp");
        dispatcher.forward(request, response);
    }

}
