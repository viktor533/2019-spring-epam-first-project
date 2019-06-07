package com.epam.servlets;

import com.epam.domain.Token;
import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.TokenService;
import com.epam.service.UserService;
import com.epam.state.Constants;
import com.epam.state.ServiceState;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;

public class LoginServlet extends BaseServlet {
    private UserService userService = ServiceState.getUserServiceInstance();
    private TokenService tokenService = ServiceState.getTokenServiceInstance();

    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[Constants.TOKEN_SIZE];
        random.nextBytes(bytes);
        return bytes.toString();
    }

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
            String token = generateToken();
            tokenService.save(Token.builder()
                    .token(token)
                    .userId(user.getId())
                    .role(user.getRole())
                    .build());
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);

            if (UserRole.ADMIN.equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/pages/hotel_page.jsp");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/hotel_page.jsp");
//                dispatcher.forward(request, response);
            } else {
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user_page.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
