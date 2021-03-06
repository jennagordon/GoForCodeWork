<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
    <title>Organization</title>
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
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organization/mainpage">Organization</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/mainpage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/mainpage">Sighting</a></li>
        </ul>
    </div>

    <div class="col-lg-6 col-sm-6">
        <h2>List of Organizations</h2>
        <table class="table table-hover">

            <tr>
                <th width="60%">Organization Name</th>
                <th width="40%"></th>
            </tr>
            <c:forEach items="${viewModel.organizations}" var="organization">
                <tr>
                    <td><a href="/organization/details?id=${organization.id}">${organization.name}</a></td>
                    <td>
                        <a href="/organization/edit?id=${organization.id}">Edit</a> |
                        <a href="/organization/delete?id=${organization.id}"
                           onclick="return confirm('Are you sure you want to delete this organization?')">Delete</a>

                    </td>
                </tr>

            </c:forEach>
        </table>
        <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
            <a href="/organization/mainpage?offset=${(pageNumber - 1) * 5}">${pageNumber}</a>
        </c:forEach>
    </div>

    <div class="col-lg-6 col-sm-6">
        <h2>Create Organization</h2>

        <div>
            <sf:form action="/organization/create" method="post" modelAttribute="commandModel" class="form-horizontal"
                     role="form" >
                <label for="name" class="col-lg-3 control-label">Name:</label>
                <div class="col-lg-10">
                    <sf:input id="name" path="name" class="form-control"/> <sf:errors path="name"/>
                </div>


                <label for="description" class="col-lg-3 control-label">Description:</label>
                <div class="col-lg-8">
                    <sf:input id="description" path="description" class="form-control"/> <sf:errors path="description"/>
                </div>

                <label for="location" class="col-lg-3 control-label">Locations:</label>
                <div class="col-lg-8">
                    <sf:select path="locationId" name="location" class="form-control">
                        <sf:option value="" label="No Location"/>
                        <sf:options items="${viewModel.locations}" itemValue="id" itemLabel="name"/>
                    </sf:select>
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
</html>
