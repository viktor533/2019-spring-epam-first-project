package com.epam.servlets;

import com.epam.domain.Hotel;
import com.epam.domain.Token;
import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.HotelService;
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
    static private UserService userService = ServiceState.getUserServiceInstance();
    static private TokenService tokenService = ServiceState.getTokenServiceInstance();
    static private HotelService hotelService = ServiceState.getHotelServiceInstance();

    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[Constants.TOKEN_SIZE];
        random.nextBytes(bytes);
        return bytes.toString();
    }

    public static User checkToken(HttpServletRequest request) {
        boolean isGoodToken = false;
        if (request.getCookies().length > 0) {
            for (Cookie cookie : request.getCookies()) {
                if (Constants.COOKIE_PARAM.equals(cookie.getName())) {
                    String tokenId = request.getCookies()[0].getValue();
                    Token token = tokenService.findById(tokenId);
                    if (token != null) {
                        return userService.findById(token.getUserId());
                    }
                }
            }
        }
        return null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            Cookie cookie = new Cookie(Constants.COOKIE_PARAM, token);
            response.addCookie(cookie);

            if (UserRole.ADMIN.equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/hotel");

//                Hotel hotel = hotelService.findById(1L);
//                request.setAttribute("hotel", hotel);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/hotel_page.jsp");
//                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/user?userId="+user.getId());

//                request.setAttribute("user", user);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user_page.jsp");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/user?userId="+user.getId());
//                dispatcher.forward(request, response);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login_page.jsp");
        dispatcher.forward(request, response);
    }
}
