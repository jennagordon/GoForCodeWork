<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/16/2018
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Superhero Edit</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>

<div class="col-lg-6 container">
<h2>Edit</h2>


<sf:form action="/sighting/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="sightingId"/>
    Date: <sf:input path="date" class="form-control"/>  <sf:errors path="date"/><br>
    Description: <sf:input path="description" class="form-control"/> <sf:errors path="description"/> <br>
    Heroes: <sf:select path="heroIds" name="hero" class="form-control">
            <sf:options items="${viewModel.heroes}" itemValue="id" itemLabel="name"/>
            </sf:select>
            <sf:errors path="heroIds"/>
    Locations: <sf:select path="locationId" name="location" class="form-control">
               <sf:options items="${viewModel.locations}" itemValue="id" itemLabel="name"/>
               </sf:select>
               <sf:errors path="locationId"/>
    <br>

    <a href="/sighting/mainpage">
        <button type="button" class="btn btn-danger">Cancel</button>
    </a>
    <button type="submit" class="btn btn-primary">Edit</button>
</sf:form>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
