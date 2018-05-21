package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Controller
public class FlooringCalculatorController {

    @RequestMapping(value = "flooringCalculator", method = RequestMethod.POST)
    public String flooringCalculator(HttpServletRequest request, Map<String, Object> model) {
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

        // create request dispatcher
        model.put("totalCost", totalCost);
        model.put("totalTime", totalTimeHour);


        // forward
        return "result";
    }


}
