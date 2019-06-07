package com.epam.servlets;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.UserService;
import com.epam.state.ServiceState;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends BaseServlet {
    private UserService userService = ServiceState.getUserServiceInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =  request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.findByLogin(email);
        if (user == null) {
            request.setAttribute("message", "User with such mail not found");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login_page.jsp");
            dispatcher.forward(request, response);
        } else if (!user.getPassword().equals(password)) {
            request.setAttribute("message", "Invalid password entered");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login_page.jsp");
            dispatcher.forward(request, response);
        } else {
            if (UserRole.ADMIN.equals(user.getRole())) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/hotel_page.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user_page.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
