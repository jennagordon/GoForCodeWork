package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Controller
public class TipCalculatorController {

    @RequestMapping(value = "tipCalculator", method = RequestMethod.POST)
    public String calculateTip(HttpServletRequest request, Map<String, Object> model) {

        BigDecimal billAmount = new BigDecimal(request.getParameter("billAmount"));
        BigDecimal tipPercentage = new BigDecimal(request.getParameter("tipPercentage"));

        BigDecimal tipAmount = billAmount.multiply((tipPercentage.movePointLeft(2))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalBill = billAmount.add(tipAmount).setScale(2, RoundingMode.HALF_UP);

        model.put("billAmount", billAmount);
        model.put("tipPercentage", tipPercentage);
        model.put("tipAmount", tipAmount);
        model.put("totalBill", totalBill);

        return "result";
    }
}
