package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FactorizorController {

    @RequestMapping(value = "factorNumber", method = RequestMethod.POST)
    public String factorNumber(HttpServletRequest request, Map<String, Object> model) {

        List<Integer> factorList = new ArrayList<>();
        int factorSum = 0;
        boolean isPrime = false;
        boolean isPerfect = false;
        // number to factor came from the name we assigned in the jsp
        // get item from the form
        String input = request.getParameter("numberToFactor");
        int numberToFactor = Integer.parseInt(input);

        for(int i =1; i < numberToFactor; i++) {
            if(numberToFactor % i ==0) {
                factorList.add(i);
                factorSum += i;
            }
        }

        if (factorSum == numberToFactor) {
            isPerfect = true;
        }

        if(factorSum == 1) {
            isPrime = true;
        }


        // set that value on the model map
        // and from our code calculations
        model.put("numberToFactor", numberToFactor);
        model.put("factors", factorList);
        model.put("isPrime", isPrime);
        model.put("isPerfect", isPerfect);

        // returns logical string name of view (result.jsp)
        return "result";

// BEFORE PUTTING IN THE CALC LOGIC

//        // get value out of form
//        String numberToFactor = request.getParameter("numberToFactor");
//
//        // put that on the model (key name, object)
//        model.put("numberToFactor", numberToFactor);
//
//

    }
}
