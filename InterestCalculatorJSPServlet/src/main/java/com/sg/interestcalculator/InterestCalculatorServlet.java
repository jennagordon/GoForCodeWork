package com.sg.interestcalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InterestCalculatorServlet")
public class InterestCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get user inputs
        String inputInterestRate = request.getParameter("interestRate");
        String inputPrinciple = request.getParameter("principle");
        String inputYears = request.getParameter("yearsInFund");

        // convert inputs to Big Decimal
        BigDecimal interestRate = new BigDecimal(inputInterestRate);
        BigDecimal originalPrinciple = new BigDecimal(inputPrinciple);
        int years = Integer.parseInt(inputYears);

        BigDecimal runningBalance = originalPrinciple;
        // doing a piece of the calc up here to make below more clear
        interestRate = interestRate.divide(new BigDecimal(4));
        BigDecimal interestEarned, yearlyInterestEarned = new BigDecimal(0);
        BigDecimal oneHundred = new BigDecimal(100);
        BigDecimal quarterlyRunningBalance = runningBalance;

        // set up lists to use for print out
        List<Year> yearList = new ArrayList<>();

        for(int i = 1; i <= years; i++) {
            Year year = new Year();
            year.setYear(i);
            year.setStartingBalance(runningBalance);

            yearList.add(year);
            // running balance at i=1 is the original principle
            // for each year it is the total of the previous year's end



            for(int quarter = 0; quarter < 4; quarter++) {

                // interest rate is already divided into quarters so don't need to here
                interestEarned = quarterlyRunningBalance.multiply(interestRate).divide(oneHundred, 2, RoundingMode.HALF_UP);
                yearlyInterestEarned = yearlyInterestEarned.add(interestEarned);
                //add to itself since the loop will run 4 times (each quarter)
                quarterlyRunningBalance = quarterlyRunningBalance.add(interestEarned);
            }

            runningBalance = runningBalance.add(yearlyInterestEarned);

            year.setEndingBalance(runningBalance.add(yearlyInterestEarned));
            year.setInterestEarned(yearlyInterestEarned);

        }

        request.setAttribute("yearsList", yearList);


        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
