<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/16/2018
  Time: 10:23 AM
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


<sf:form action="/hero/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="id"/>
    Name: <sf:input path="name" class="form-control"/>  <sf:errors path="name"/> <br>
    Description: <sf:input path="description" class="form-control"/>  <sf:errors path="description"/> <br>
    Powers: <sf:select path="powerIds" name="power" class="form-control">
            <sf:options items="${viewModel.powers}" itemValue="id" itemLabel="name"/>
            </sf:select>
            <sf:errors path="powerIds"/>
    Organizations: <sf:select path="organizationIds" name="organization" class="form-control">
                   <sf:options items="${viewModel.organizations}" itemValue="id" itemLabel="name"/>
                   </sf:select>
                   <sf:errors path="organizationIds"/>

    <br>

    <a href="/hero/mainpage">
        <button type="button" class="btn btn-danger">Cancel</button>
    </a>
    <button type="submit" class="btn btn-primary">Edit</button>
</sf:form>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
