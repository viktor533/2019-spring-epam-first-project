package com.epam.servlets;


import com.epam.domain.Bill;
import com.epam.domain.User;
import com.epam.service.BillService;
import com.epam.service.UserService;
import com.epam.state.RepositoryState;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BillServlet extends BaseServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BillService billService = new BillService(RepositoryState.getBillRepositoryInstance());
        Bill bill = billService.findById(1L);
        request.setAttribute("bill", bill);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bill.jsp");
        dispatcher.forward(request, response);
    }
}
