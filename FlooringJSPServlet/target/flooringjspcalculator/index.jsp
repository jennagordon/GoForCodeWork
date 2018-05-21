<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Flooring Calculator</title>
</head>
<body>
<h2>Flooring Calculator</h2>
<form method="POST" action="FlooringServlet">
<p>
    Please enter the width of the flooring area
    <input type="text" name="width"/>
</p>
<p>
    Please enter the length of the flooring area
    <input type="text" name="length">
</p>
<p>
    Please enter the cost of the material per square foot
    <input type="text" name="cost">
</p>
    <input type="submit" value="Calculate">
</form>


</body>
</html>
