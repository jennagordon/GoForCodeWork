package com.sg.tipcalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet(name = "TipCalculatorServlet")
public class TipCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BigDecimal billAmount = new BigDecimal(request.getParameter("billAmount"));
        BigDecimal tipPercentage = new BigDecimal(request.getParameter("tipPercentage"));

        BigDecimal tipAmount = billAmount.multiply((tipPercentage.movePointLeft(2))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalBill = billAmount.add(tipAmount).setScale(2, RoundingMode.HALF_UP);

        request.setAttribute("billAmount", billAmount);
        request.setAttribute("tipPercentage", tipPercentage);
        request.setAttribute("tipAmount", tipAmount);
        request.setAttribute("totalBill", totalBill);

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
