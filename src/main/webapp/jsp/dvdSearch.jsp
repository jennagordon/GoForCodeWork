<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>DVD Library</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <h2>Search Results</h2>
    <hr>
    <div id="header" class="container">
        <%--<br>--%>
        <%--<div class="col-md-3">--%>
            <%--<a href="${pageContext.request.contextPath}/displayAddForm">--%>
                <%--<button id="create-dvd-button" class="btn btn-default" type="button">--%>
                    <%--Create Dvd--%>
                <%--</button>--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<form action="/searchDvd" method="GET">--%>
            <%--<div class="col-md-1 right">--%>
                <%--<button id="search-button" class="btn btn-default" type="submit">--%>
                    <%--Search--%>
                <%--</button>--%>
            <%--</div>--%>
            <%--<div class="col-md-3">--%>

                <%--<select id="search-category" class="form-control">--%>
                    <%--<option value="" selected disabled hidden>Search Category</option>--%>
                    <%--<option name="searchTerm" value="title">Title</option>--%>
                    <%--<option value="release-year">Release Year</option>--%>
                    <%--<option value="director-name">Director Name</option>--%>
                    <%--<option value="rating">Rating</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<div class="col-md-3">--%>
                <%--<input type="text"--%>
                       <%--id="search-term"--%>
                       <%--class="form-control"--%>
                       <%--name="userSearch"--%>
                       <%--placeholder="Search Term" required/>--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</div>--%>
    <%--<div id="hr-header">--%>
        <%--<hr>--%>
    <%--</div>--%>

    <div>
        <table class="table table-bordered">
            <tr>
                <th>Title</th>
                <th>Release Year</th>
                <th>Director</th>
                <th>Rating</th>
                <th></th>

            </tr>
            <c:forEach var="currentDvd" items="${dvdSearchList}">
                <tr>
                    <td>
                        <a href="displayDvd?dvdId=${currentDvd.dvdId}">
                            <c:out value="${currentDvd.title}"/>
                        </a>
                    </td>
                    <td>
                        <c:out value="${currentDvd.releaseYear}"/>
                    </td>
                    <td>
                        <c:out value="${currentDvd.director}"/>
                    </td>
                    <td>
                        <c:out value="${currentDvd.rating}"/>
                    </td>
                    <td>
                        <a href="displayEditForm?dvdId=${currentDvd.dvdId}">Edit</a> | <a href="deleteDvd?dvdId=${currentDvd.dvdId}"
                                                                                          onclick="return confirm('Are you sure you want to delete this DVD?')">Delete</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </div>

            <a href="${pageContext.request.contextPath}/">
                <button type="submit" class="btn btn-primary">
                    Return To Home
                </button>
            </a>

</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

