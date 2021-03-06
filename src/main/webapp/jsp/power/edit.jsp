<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/16/2018
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Superhero Edit</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
<div class="col-lg-6 container">
<h2>Edit ${commandModel.name}</h2>


<sf:form action="/power/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="powerId"/>
    Name: <sf:input path="name" class="form-control"/> <sf:errors path="name"/> <br>
    Description: <sf:input path="description" class="form-control"/> <sf:errors path="description"/><br>

    <a href="/power/mainpage"><button type="button" class="btn btn-danger">Cancel</button></a>
    <button type="submit" class="btn btn-primary">Edit</button>
</sf:form>
</div>

    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
