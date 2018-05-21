package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

@Controller
public class LuckySevensController {

    @RequestMapping(value = "luckySevens", method = RequestMethod.POST)
    public String luckySevens(HttpServletRequest request, Map<String, Object> model) {

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

        model.put("betAmount", betAmount);
        model.put("availableFunds", availableFunds);
        model.put("numberOfRolls", numberOfRolls);
        model.put("maxNumberOfRolls", maxNumberOfRolls);
        model.put("maxMoney", maxMoney);

        return "result";
    }
}
