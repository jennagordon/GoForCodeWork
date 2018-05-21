<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Hero</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <br>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/hero/mainpage">Hero</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/mainpage">Power</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/mainpage">Organization</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/mainpage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/mainpage">Sighting</a></li>
        </ul>
    </div>

    <div class="col-lg-6 col-sm-6">
        <h2>List of Heroes</h2>
        <table class="table table-hover">

            <tr>
                <th width="60%">Hero Name</th>
                <th width="40%"></th>
            </tr>
            <c:forEach items="${viewModel.heroes}" var="hero">
                <tr>
                    <td><a href="/hero/details?id=${hero.id}">${hero.name}</a></td>
                    <td>
                        <a href="/hero/edit?id=${hero.id}">Edit</a> |
                        <a href="/hero/delete?id=${hero.id}"
                           onclick="return confirm('Are you sure you want to delete this hero?')">Delete</a>

                    </td>
                </tr>

            </c:forEach>
        </table>
        <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
            <a href="/hero/mainpage?offset=${(pageNumber - 1) * 5}">${pageNumber}</a>
        </c:forEach>
    </div>

    <div class="col-lg-6 col-sm-6">
        <h2>Create Hero</h2>

        <div>
            <sf:form action="/hero/create" method="post" modelAttribute="commandModel" class="form-horizontal"
                     role="form" >
                <label for="name" class="col-lg-3 control-label">Name:</label>
                <div class="col-lg-10">
                    <sf:input id="name" path="name" class="form-control"/> <sf:errors path="name"/>
                </div>

                <label for="description" class="col-lg-3 control-label">Description:</label>
                <div class="col-lg-8">
                    <sf:input id="description" path="description" class="form-control"/> <sf:errors path="description"/>
                </div>

                <label for="power" class="col-lg-3 control-label">Powers:</label>
                <div class="col-lg-8">
                    <sf:select path="powerIds" name="power" class="form-control">
                        <sf:option value="" label="Select"/>
                        <sf:options items="${viewModel.powers}" itemValue="id" itemLabel="name"/>
                    </sf:select>
                    <sf:errors path="powerIds"/>
                </div>

                <label for="organization" class="col-lg-3 control-label">Organizations:</label>
                <div class="col-lg-8">
                    <sf:select path="organizationIds" name="organization" class="form-control">
                        <sf:option value="" label="Select"/>
                        <sf:options items="${viewModel.organizations}" itemValue="id" itemLabel="name"/>
                    </sf:select>
                    <sf:errors path="organizationIds"/>
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
