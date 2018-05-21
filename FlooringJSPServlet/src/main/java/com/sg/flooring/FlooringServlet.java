package com.sg.flooring;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet(name = "FlooringServlet")
public class FlooringServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BigDecimal laborCost;
        // taking in the string from user inputs
        String inputWidth = request.getParameter("width");
        String inputLength = request.getParameter("length");
        String inputCost = request.getParameter("cost");
        // converting string into big decimal
        BigDecimal width = new BigDecimal(inputWidth);
        BigDecimal length = new BigDecimal(inputLength);
        BigDecimal cost = new BigDecimal(inputCost);

        // calculate square feet (area) width * length
        BigDecimal area = width.multiply(length);


        // calculate the hourly rate area / 20
        // variable for total time
        BigDecimal totalTimeHour = area.divide(new BigDecimal(20));
        // put it into mins
        BigDecimal totalTimeMins = totalTimeHour.multiply(new BigDecimal(60));
        // get how many 15 min increments and round
        BigDecimal numQuarters = totalTimeMins.divide(new BigDecimal(15)).setScale(0, RoundingMode.UP);
        // multiply back into hours
        BigDecimal totalTimeCharged = numQuarters.divide(new BigDecimal(4));


        laborCost = totalTimeCharged.multiply(new BigDecimal(86));
        //calculate total cost area + labor cost
        BigDecimal materialCost = cost.multiply(area);
        BigDecimal totalCost = laborCost.add(materialCost);


        // setting values on the request
        // string is the KEY name not the input
        request.setAttribute("totalCost", totalCost);
        request.setAttribute("totalTime", totalTimeHour);

        // create request dispatcher
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

        // forward
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
}
