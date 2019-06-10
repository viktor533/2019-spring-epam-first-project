package com.epam.servlets;

import com.epam.domain.Token;
import com.epam.domain.User;
import com.epam.service.TokenService;
import com.epam.service.UserService;
import com.epam.state.Constants;
import com.epam.state.ServiceState;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserServlet extends BaseServlet {
    private UserService userService = ServiceState.getUserServiceInstance();
    private TokenService tokenService = ServiceState.getTokenServiceInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        Long userId = null;
        if (userIdStr != null) {
            userId = Long.parseLong(userIdStr);
        }
        log.debug("Accept userId: " + userId);

        User user = LoginServlet.checkToken(request);
        if (user == null || user.getId() != userId) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bad_session_page.jsp");
            dispatcher.forward(request, response);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user_page.jsp");
        dispatcher.forward(request, response);
    }

}
