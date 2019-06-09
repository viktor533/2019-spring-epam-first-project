package com.epam.servlets;

import com.epam.domain.Hotel;
import com.epam.domain.User;
import com.epam.service.HotelService;
import com.epam.service.UserService;
import com.epam.state.ServiceState;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends BaseServlet {
    private UserService userService = ServiceState.getUserServiceInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Iterable<User> users = userService.findAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
