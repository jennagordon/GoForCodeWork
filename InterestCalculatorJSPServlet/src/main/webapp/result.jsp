<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 3/13/2018
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Interest Calculator</title>
</head>
<body>
<h2>Result</h2>

<p>
    <c:forEach var="year" items="${yearsList}">
<div>
    Year: ${year.year} <br>
    Starting Balance: ${year.startingBalance} <br>
    Ending Balance: ${year.endingBalance} <br>
    Interest Earned: ${year.interestEarned} <br>
</div>
<hr>
</c:forEach>
</p>


<a href="index.jsp">Calculate Again</a>
</body>
</html>
