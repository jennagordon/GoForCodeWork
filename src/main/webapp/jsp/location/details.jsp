<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/16/2018
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>Details</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">

    </head>
<body>
<div class="text-center container">
<h2>Location Details</h2>

Name: ${viewModel.name}
<br>
Description: ${viewModel.description}
<br>
Street: ${viewModel.street}
<br>
City: ${viewModel.city}
<br>
State: ${viewModel.state}
<br>
Zip Code: ${viewModel.zip}
<br>
Latitude: ${viewModel.latitude}
<br>
Longitude: ${viewModel.longitude}
<br>

<a href="/location/mainpage"><button type="button" class="btn btn-primary">Return to Location</button></a>
</div>
</body>
</html>
