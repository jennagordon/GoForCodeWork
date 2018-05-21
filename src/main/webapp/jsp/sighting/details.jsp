<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/16/2018
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">

</head>
<body>
<div class="text-center container">
<h2>Sighting Details</h2>

Date: ${viewModel.date}
<br>
Description: ${viewModel.description}
<br>
Heroes: <c:forEach items="${viewModel.heroes}" var="hero">
        <a href="${pageContext.request.contextPath}/hero/details?id=${hero.id}"> ${hero.name}</a> <br>
        </c:forEach>
<br>
Location: ${viewModel.locationName}
<br>

<a href="/sighting/mainpage"><button type="button" class="btn btn-primary">Return to Sighting</button></a>
</div>

</body>
</html>
