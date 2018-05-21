<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Superhero</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <br>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hero/mainpage">Hero</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/mainpage">Power</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/mainpage">Organization</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/mainpage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/mainpage">Sighting</a></li>
        </ul>
    </div>
    <h2>Superhero Home Page</h2>

    <div>
        With the rising popularity of superhero movies there has been a heightened
        awareness of superheroes in our midst.  The frequency of superhero sightings
        is increasing at an alarming rate.
    </div>

    <br>

    <div>
        <div class="row">
            <div class="col-xs-8 col-sm-12 col-md-12 col-lg-12">
                <table class="table table-responsive table-striped">
                    <thead>
                    <tr>
                        <th width="15%">Date</th>
                        <th width="25%">Location</th>
                        <th width="25%">Hero/Heroes</th>
                        <th width="35%">Description</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${viewModel.sightings}" var="sighting">
                        <tr>
                            <td> ${sighting.date}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/location/details?id=${sighting.location.id}">${sighting.location.name}</a>
                            </td>
                            <td>
                                <c:forEach items="${sighting.heroes}" var="hero">
                                    <a href="${pageContext.request.contextPath}/hero/details?id=${hero.id}"> ${hero.name}</a><br>
                                </c:forEach>
                            </td>
                            <td> ${sighting.description}</td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

