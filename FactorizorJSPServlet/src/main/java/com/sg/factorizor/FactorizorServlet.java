package com.sg.factorizor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FactorizorServlet")
public class FactorizorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


        // set that value on the request from the form
        // and from our code calculations
        request.setAttribute("numberToFactor", numberToFactor);
        request.setAttribute("factors", factorList);
        request.setAttribute("isPrime", isPrime);
        request.setAttribute("isPerfect", isPerfect);

        // get request dispatcher to forward
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

        //forward the request
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
}
