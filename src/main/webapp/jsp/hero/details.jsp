<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/16/2018
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Details</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">

</head>
<body>
<div class="text-center container">
<h2>Hero Details</h2>

Name: ${viewModel.name}
<br>
Description: ${viewModel.description}
<br>
Powers: <c:forEach items="${viewModel.powers}" var="power">
    <a href="${pageContext.request.contextPath}/power/details?id=${power.id}"> ${power.name}</a> <br>
</c:forEach>

Organizations: <c:forEach items="${viewModel.organizations}" var="organization">
    <a href="${pageContext.request.contextPath}/organization/details?id=${organization.id}"> ${organization.name}</a> <br>
</c:forEach>
<br>

<a href="/hero/mainpage"><button type="button" class="btn btn-primary">Return to Heroes</button></a>
</div>
</body>
</html>
