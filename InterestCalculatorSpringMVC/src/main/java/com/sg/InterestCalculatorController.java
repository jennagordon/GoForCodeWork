package com.sg;

import com.sg.DTO.Year;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InterestCalculatorController {

    @RequestMapping(value = "interestCalculator", method = RequestMethod.POST)
    public String calculateInterest(HttpServletRequest request, Map<String, Object> model) {

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

        model.put("yearsList", yearList);

        return "result";
    }
}
