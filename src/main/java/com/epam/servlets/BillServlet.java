package com.epam.servlets;

import com.epam.domain.Bill;
import com.epam.domain.User;
import com.epam.service.BillService;
import com.epam.state.RepositoryState;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BillServlet extends BaseServlet {

    public static boolean haveBill(User user, Long billId) {
        for (Bill bill : user.getBills()) {
            if (bill.getId() == billId) {
                return true;
            }
        }
        return false;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BillService billService = new BillService(RepositoryState.getBillRepositoryInstance());
        long billId = Long.parseLong(request.getParameter("billId"));

        User user = LoginServlet.checkToken(request);
        if (user == null || !haveBill(user, billId)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bad_session_page.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Bill bill = billService.findById(billId);
        request.setAttribute("bill", bill);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bill_bage.jsp");
        dispatcher.forward(request, response);
    }
}
