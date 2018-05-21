<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title>Superhero</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <br>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hero/mainpage">Hero</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/mainpage">Power</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/mainpage">Organization</a>
            </li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location/mainpage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/mainpage">Sighting</a></li>
        </ul>
    </div>

    <div class=" col-lg-3 alert-danger text-center">
    <c:if test="${not empty message}">
        <spring:message code="${message}" />
    </c:if>
</div>
    <div class="col-lg-6 col-sm-6">
        <h2>List of Locations</h2>

        <table class="table table-hover">

            <tr>
                <th width="60%">Location Name</th>
                <th width="40%"></th>
            </tr>
            <c:forEach items="${viewModel.locations}" var="location">
                <tr>
                    <td><a href="/location/details?id=${location.id}">${location.name}</a></td>
                    <td>
                        <a href="/location/edit?id=${location.id}">Edit</a> |
                        <a href="/location/delete?id=${location.id}"
                           onclick="return confirm('Are you sure you want to delete this location?')">Delete</a>

                    </td>
                </tr>

            </c:forEach>
        </table>
        <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
            <a href="/location/mainpage?offset=${(pageNumber - 1) * 5}">${pageNumber}</a>
        </c:forEach>
    </div>

    <div class="col-lg-6 col-sm-6">
        <h2>Create Location</h2>

        <div>
            <sf:form action="/location/create" method="post" modelAttribute="commandModel" class="form-horizontal"
                     role="form" >
                <label for="name" class="col-lg-3 control-label">Name:</label>
                <div class="col-lg-10">
                    <sf:input id="name" path="name" class="form-control"/> <sf:errors path="name"/>
                </div>

                <label for="description" class="col-lg-3 control-label">Description:</label>
                <div class="col-lg-8">
                    <sf:input id="description" path="description" class="form-control"/> <sf:errors path="description"/>
                </div>

                <label for="street" class="col-lg-3 control-label">Street:</label>
                <div class="col-lg-8">
                    <sf:input id="street" path="street" class="form-control"/> <sf:errors path="street"/>
                </div>

                <label for="city" class="col-lg-3 control-label">City:</label>
                <div class="col-lg-8">
                    <sf:input id="city" path="city" class="form-control"/> <sf:errors path="city"/>
                </div>

                <label for="state" class="col-lg-3 control-label">State:</label>
                <div class="col-lg-8">
                    <sf:input id="state" path="state" class="form-control"/> <sf:errors path="state"/>
                </div>

                <label for="zip" class="col-lg-3 control-label">Zip Code:</label>
                <div class="col-lg-8">
                    <sf:input id="zip" path="zip" class="form-control"/> <sf:errors path="zip"/>
                </div>

                <label for="latitude" class="col-lg-3 control-label">Latitude:</label>
                <div class="col-lg-8">
                    <sf:input id="latitude" path="latitude" class="form-control"/> <sf:errors path="latitude"/>
                </div>

                <label for="longitude" class="col-lg-3 control-label">Longitude:</label>
                <div class="col-lg-8">
                    <sf:input id="longitude" path="longitude" class="form-control"/> <sf:errors path="longitude"/>
                </div>

                <br>

                <button type="submit" class="btn btn-primary">Create</button>

            </sf:form>
        </div>



    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>

</body>
</html>
