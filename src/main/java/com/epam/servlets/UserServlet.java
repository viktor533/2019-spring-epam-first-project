package com.epam.servlets;

import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.state.ServiceState;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserServlet extends BaseServlet {
    private static UserService userService = ServiceState.getUserServiceInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        Long userId = null;
        if (userIdStr != null) {
            userId = Long.parseLong(userIdStr);
        }
        log.debug("Accept userId: " + userId);
        User user = userService.findById(userId);
        log.debug("Send user: " + user);

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user_page.jsp");
        dispatcher.forward(request, response);
    }

}
