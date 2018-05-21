<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 3/16/2018
  Time: 8:30 AM
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
<div id="edit-dvd-form" class="col-lg-8 col-sm-8">
    <h2>Edit Dvd</h2>
    <hr>
    <sf:form class="form-horizontal" id="edit-form" modelAttribute="dvd" action="editDvd" method="POST" novalidate="novalidate">
        <div class="form-group">
            <label for="edit-dvd-title" class="col-lg-2 col-sm-2">
                Dvd Title:
            </label>

            <div class="col-lg-8 col-sm-8">
                <sf:input type="text"
                       class="form-control left"
                       id="edit-dvd-title"
                       name="edit-title" path="title"
                       placeholder="Enter Dvd Title"/>
                <sf:errors path="title" cssClass="error"></sf:errors>
            </div>
        </div>

        <div class="form-group">
            <label for="edit-release-year" class="col-lg-2 col-sm-2">
                Release Year:
            </label>

            <div class="col-lg-8 col-sm-8">
                <sf:input type="text"
                       class="form-control left"
                       id="edit-release-year"
                       name="edit-releaseYear"
                       path="releaseYear"
                       placeholder="Enter Release Year" />
                <sf:errors path="releaseYear" cssClass="error"></sf:errors>
            </div>
        </div>

        <div class="form-group">
            <label for="edit-director" class="col-lg-2 col-sm-2">
                Director:
            </label>

            <div class="col-lg-8 col-sm-8">
                <sf:input type="text"
                       class="form-control left"
                       id="edit-director"
                       name="edit-director"
                       path="director"
                       placeholder="Enter Director" />
                <sf:errors path="director" cssClass="error"></sf:errors>
            </div>
        </div>

        <div class="form-group">
            <label for="edit-rating" class="col-lg-2 col-sm-2">
                Rating:
            </label>
            <div class="col-lg-4 col-sm-4">
                <sf:select id="edit-rating" class="form-control" name="edit-rating" path="rating">
                    <sf:option value="">Choose Rating</sf:option>
                    <sf:option value="G">G</sf:option>
                    <sf:option value="PG">PG</sf:option>
                    <sf:option value="PG-13">PG-13</sf:option>
                    <sf:option value="R">R</sf:option>
                </sf:select>
                <sf:errors path="rating" cssClass="error"></sf:errors>
            </div>

        </div>
        <div class="form-group">
            <label for="edit-notes" class="col-lg-2 col-sm-2">
                Notes:
            </label>
            <div class="col-lg-8 col-sm-8">
                  <sf:input class="form-control" id="edit-notes" type="text"
                            name="edit-notes" placeholder="Enter Note" path="notes" />

            </div>
        </div>

        <div class="col-lg-2 col-sm-2">
            <a href="${pageContext.request.contextPath}/">
            <button type="button" id="edit-cancel-button"
                    class="btn btn-danger">
                Cancel
            </button>
            </a>
        </div>
        <div class="col-lg-2 col-sm-2">

            <a href="${pageContext.request.contextPath}/">
            <button type="submit" id="edit-dvd-button"
                    class="btn btn-primary">
                Update Dvd
            </button>
            </a>

        </div>
        <sf:hidden path="dvdId"/>

    </sf:form>
</div>

</body>
</html>
