package com.epam.servlets;

import com.epam.domain.Booking;
import com.epam.domain.enums.RoomClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class BookingServlet extends BaseServlet  {

    private static Booking getDemoBooking() {
        Booking booking = Booking.builder()
                .id(1)
                .roomClass(RoomClass.STANDART)
                .start(LocalDate.now())
                .end(LocalDate.now().plusDays(3))
                .build();
        return booking;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");
        Long bookingId = null;
        if (bookingIdStr != null) {
            bookingId = Long.parseLong(bookingIdStr);
        }

        log.debug("Accept bookingId: " + bookingId);
        Booking booking = getDemoBooking();
//        log.debug("Send booking: " + booking);
        request.setAttribute("booking", booking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/booking_page.jsp");
        dispatcher.forward(request, response);
    }
}
