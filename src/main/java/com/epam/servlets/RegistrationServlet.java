package com.epam.servlets;

import com.epam.domain.enums.UserRole;
import com.epam.service.UserService;
import com.epam.state.RepositoryState;
import com.epam.utils.PrepStatement;
import java.io.IOException;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

public class RegistrationServlet extends HttpServlet {

    private final static UserService userService = new UserService(
        RepositoryState.getUserRepositoryInstance());
    private static final String INSERT_INTO_USER = "INSERT INTO USER (LOGIN, PASSWORD, ROLE) VALUES(?, ?, ?)";



    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        System.out.println("Enter doPost method"); // Simple log

        String email = request.getParameter("email");
        String psw = request.getParameter("psw");
        String pswRepeat = request.getParameter("psw-repeat");

        boolean isAlreadyExists = userService.checkIfAlreadyExistsInDb(email);

        if (psw.equals(pswRepeat) && isAlreadyExists) {

            PreparedStatement preparedStatement = PrepStatement.getPreparedStatement(
                INSERT_INTO_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, psw);
            preparedStatement.setString(3, UserRole.USER.name());

            preparedStatement.executeUpdate();

            request.getRequestDispatcher("pages/successfulRegistration_page.jsp")
                .forward(request, response); // redirect to specified page

        } else {
            request.getRequestDispatcher(
                "pages/registration_page.jsp") // redirect to the same page if passwords do not match
                .forward(request, response);
        }

    }

}
