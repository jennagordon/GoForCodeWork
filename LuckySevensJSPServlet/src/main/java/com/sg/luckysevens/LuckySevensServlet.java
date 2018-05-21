package com.sg.luckysevens;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

@WebServlet(name = "LuckySevensServlet")
public class LuckySevensServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BigDecimal betAmount = new BigDecimal(request.getParameter("betAmount"));


        BigDecimal availableFunds = betAmount;

        int numberOfRolls = 0;
        int maxNumberOfRolls = 0;
        BigDecimal maxMoney = betAmount;


        while (!availableFunds.equals(new BigDecimal(0))) {

            Random random = new Random();


            // calculate number of rolls and amount of money
            int di1 = random.nextInt(6) + 1;
            int di2 = random.nextInt(6) + 1;
            int sum = di1 + di2;


            if (sum == 7) {
               availableFunds = availableFunds.add(new BigDecimal(4));
               if(availableFunds.compareTo(maxMoney) > 0) {
                   maxMoney = availableFunds;
                   maxNumberOfRolls = numberOfRolls;
               }

            } else {
                availableFunds = availableFunds.subtract(new BigDecimal(1));
            }
            numberOfRolls++;
        }

        // set attributes for bet amount, rolls, when to quit

        request.setAttribute("betAmount", betAmount);
        request.setAttribute("availableFunds", availableFunds);
        request.setAttribute("numberOfRolls", numberOfRolls);
        request.setAttribute("maxNumberOfRolls", maxNumberOfRolls);
        request.setAttribute("maxMoney", maxMoney);


        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
