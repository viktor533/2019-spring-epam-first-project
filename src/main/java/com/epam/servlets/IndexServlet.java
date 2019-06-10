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
public class IndexServlet extends BaseServlet {
    private final UserService userService = ServiceState.getUserServiceInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceState.getUserServiceInstance();
        Iterable<User> users = userService.findAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String delete = req.getParameter("delete");
        Long userId = null;
        if (delete != null) {
            userId = Long.parseLong(delete);
        }
        log.debug("Accept userId: " + userId);
        userService.removeById(userId);

        resp.sendRedirect(req.getContextPath());
    }
}
