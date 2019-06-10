package com.epam.servlets;

import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.UserService;
import com.epam.state.RepositoryState;
import com.epam.utils.PrepStatement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

public class RegistrationServlet extends HttpServlet {

    private static final UserService userService = new UserService(
        RepositoryState.getUserRepositoryInstance());
    private static final String INSERT_INTO_USER = "INSERT INTO USER (LOGIN, PASSWORD, ROLE) VALUES(?, ?, ?)";

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String psw = request.getParameter("psw");
        String pswRepeat = request.getParameter("psw-repeat");

        User savedUser = userService.save(User.builder()
            .login(email)
            .password(psw)
            .role(UserRole.USER)
            .id(new Random().nextLong())
            .build());

        boolean isAlreadyExists = userService.checkIfAlreadyExistsInDb(email);

            if (savedUser != null && psw.equals(pswRepeat) && !isAlreadyExists) {
                PreparedStatement preparedStatement = PrepStatement.getPreparedStatement(
                    INSERT_INTO_USER);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, psw);
                preparedStatement.setString(3, UserRole.USER.name());

                preparedStatement.executeUpdate();

            request.getRequestDispatcher("pages/successfulRegistration_page.jsp")
                .forward(request, response);
        } else {
            request.getRequestDispatcher("pages/registration_page.jsp").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/registration_page.jsp");
        dispatcher.forward(request, response);
    }

}
