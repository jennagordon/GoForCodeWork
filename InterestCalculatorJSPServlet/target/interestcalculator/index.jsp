<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Interest Calculator</title>
</head>
<body>
<h2>Interest Calculator</h2>
<form method="POST" action="InterestCalculatorServlet">
   <p>
       Annual Interest Rate:
       <input type="text" name="interestRate">

   </p>
    <p>
        Initial Amount of Principle:
        <input type="text" name="principle">
    </p>
    <p>
        Number of Years Money Stays in Fund:
        <input type="text" name="yearsInFund">
    </p>

    <input type="submit" value="Calculate">
</form>

</body>
</html>
