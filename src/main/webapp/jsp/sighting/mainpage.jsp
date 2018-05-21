<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
    <title>Sighting</title>
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
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/mainpage">Location</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting/mainpage">Sighting</a></li>
        </ul>
    </div>

    <div class="col-lg-6 col-sm-6">
        <h2>List of Sightings</h2>
        <table class="table table-hover">

            <tr>
                <th width="60%">Sighting Location Name</th>
                <th width="40%"></th>
            </tr>
            <c:forEach items="${viewModel.sightings}" var="sighting">
                <tr>
                    <td><a href="/sighting/details?id=${sighting.id}">${sighting.name}</a></td>
                    <td>
                        <a href="/sighting/edit?id=${sighting.id}">Edit</a> |
                        <a href="/sighting/delete?id=${sighting.id}"
                           onclick="return confirm('Are you sure you want to delete this sighting?')">Delete</a>

                    </td>
                </tr>

            </c:forEach>
        </table>
        <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
            <a href="/sighting/mainpage?offset=${(pageNumber - 1) * 5}">${pageNumber}</a>
        </c:forEach>
    </div>

    <div class="col-lg-6 col-sm-6">
        <h2>Create Sighting</h2>

        <div>
            <sf:form action="/sighting/create" method="post" modelAttribute="commandModel" class="form-horizontal"
                     role="form" >
                <label for="date" class="col-lg-3 control-label">Date:</label>
                <div class="col-lg-10">
                    <sf:input id="date" path="date" class="form-control"/>
                    <sf:errors path="date"/>
                </div>

                <label for="description" class="col-lg-3 control-label">Description:</label>
                <div class="col-lg-8">
                    <sf:input id="description" path="description" class="form-control"/>
                    <sf:errors path="description"/>
                </div>

                <label for="hero" class="col-lg-3 control-label">Heroes:</label>
                <div class="col-lg-8">
                    <sf:select path="heroId" name="hero" class="form-control">
                        <sf:option value="" label="Select"/>
                        <sf:options items="${viewModel.heroes}" itemValue="id" itemLabel="name"/>
                    </sf:select>
                    <sf:errors path="heroId"/>
                </div>

                <label for="location" class="col-lg-3 control-label">Locations:</label>
                <div class="col-lg-8">
                    <sf:select path="locationId" name="location" class="form-control">
                        <sf:option value="" label="Select"/>
                        <sf:options items="${viewModel.locations}" itemValue="id" itemLabel="name"/>
                    </sf:select>
                    <sf:errors path="locationId"/>
                </div>
                <br>


                <div class="col-lg-8">
                <button type="submit" class="btn btn-primary">Create</button>
                </div>

            </sf:form>
        </div>


    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
