package com.epam.servlets;


import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.state.RepositoryState;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService(RepositoryState.getUserRepositoryInstance());
        User user = userService.findById(3L);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user_page.jsp");
        dispatcher.forward(request, response);
    }

}
