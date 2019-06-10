package com.epam.servlets;

import com.epam.domain.Bill;
import com.epam.domain.User;
import com.epam.domain.enums.UserRole;
import com.epam.service.BillService;
import com.epam.state.ServiceState;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class BillDeleteServlet extends BaseServlet {
    private BillService billService = ServiceState.getBillServiceInstance();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdStr = request.getParameter("billId");
        Long billId = null;
        if (roomIdStr != null) {
            billId = Long.parseLong(roomIdStr);
        }
        log.debug("Accept billId: " + billId);
        User user = LoginServlet.checkToken(request);
        if (user != null && (UserRole.ADMIN.equals(user.getRole()) || BillServlet.haveBill(user, billId))) {
            Bill bill = billService.removeById(billId);
            if (UserRole.USER.equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/user?userId=" + user.getId());
            } else {
                response.sendRedirect(request.getContextPath() + "/room?roomId=" + bill.getRoomId());
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bad_session_page.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }

}
