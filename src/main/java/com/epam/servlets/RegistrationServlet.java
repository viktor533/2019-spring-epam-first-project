package com.epam.servlets;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.UserService;
import com.epam.state.RepositoryState;
import java.util.Random;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

public class RegistrationServlet extends HttpServlet {

  private static final UserService userService = new UserService(RepositoryState.getUserRepositoryInstance());

  @SneakyThrows
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String email = request.getParameter("email");
    String psw = request.getParameter("psw");

    User savedUser = userService.save(User.builder()
        .login(email)
        .password(psw)
        .role(UserRole.USER)
        .id(new Random().nextLong())
        .build());

    if (savedUser != null) {
      request.getRequestDispatcher("pages/successfulRegistration_page.jsp").forward(request, response);
    } else {
      request.getRequestDispatcher("pages/registration_page.jsp").forward(request, response);
    }
  }

}
