<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tip Calculator</title>
</head>

<body>
<h2>Tip Calculator</h2>

<form method="POST" action="TipCalculatorServlet">
    <p>
        What's the bill amount?
        <input type="text" name="billAmount">
    </p>

    <p>
        What percent tip do you want to leave?
        <input type="text" name="tipPercentage">
    </p>
    <input type="submit" value="Calculate">
</form>
</body>
</html>
