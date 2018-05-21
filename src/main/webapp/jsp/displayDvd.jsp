<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 3/15/2018
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dvd Library</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div id="display-dvd-form" class="col-lg-8 col-sm-8">
        <h2>Dvd Details</h2>
        <hr>
        <div>
            <p>
                Title: ${dvd.title}
            </p>
            <p>
                Release Year: ${dvd.releaseYear}
            </p>
            <p>
                Director: ${dvd.director}
            </p>
            <p>
                Rating: ${dvd.rating}
            </p>
            <p>
                Notes: ${dvd.notes}
            </p>

            <a href="${pageContext.request.contextPath}/">
            <button type="submit" class="btn btn-primary">
                Return To Home
            </button>
            </a>
        </div>
    </div>
</div>

</body>
</html>
