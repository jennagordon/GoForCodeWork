<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 3/15/2018
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dvd Library</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div id="add-dvd-form" class="col-lg-8 col-sm-8">
        <h2>Create Dvd</h2>
        <hr>
        <sf:form class="form-horizontal" id="add-form" action="addDvd" method="POST" novalidate="novalidate" modelAttribute="dvd">
            <div class="form-group">
                <label for="add-dvd-title" class="col-lg-2 col-sm-2">
                    Dvd Title:
                </label>

                <div class="col-lg-8 col-sm-8">
                    <sf:input type="text"
                           class="form-control left"
                           id="add-dvd-title"
                           name="title"
                           placeholder="Enter Dvd Title" path="title" />
                    <sf:errors path="title" cssClass="error"></sf:errors>

                </div>
            </div>

            <div class="form-group">
                <label for="add-release-year" class="col-lg-2 col-sm-2">
                    Release Year:
                </label>

                <div class="col-lg-6 col-sm-6">
                <%--<div class="input-group date" data-provide="datepicker">--%>
                    <%--<sf:input id="add-release-year" type="text" class="form-control" path="releaseYear" />--%>
                    <%--<div class="input-group-addon">--%>
                        <%--<span class="glyphicon glyphicon-th"></span>--%>
                    <%--</div>--%>
                <%--</div>--%>

                    <sf:input type="text"
                           class="form-control left"
                           id="add-release-year"
                           name="releaseYear"
                              path="releaseYear"
                           placeholder="Enter Release Year" />
                    <sf:errors path="releaseYear" cssClass="error"></sf:errors>

                </div>
            </div>

            <div class="form-group">
                <label for="add-director" class="col-lg-2 col-sm-2">
                    Director:
                </label>

                <div class="col-lg-8 col-sm-8">
                    <sf:input type="text"
                           class="form-control left"
                           id="add-director"
                           name="director"
                              path="director"
                           placeholder="Enter Director"/>
                    <sf:errors path="director" cssClass="error"></sf:errors>

                </div>
            </div>

            <div class="form-group">
                <label for="add-rating" class="col-lg-2 col-sm-2">
                    Rating:
                </label>
                <div class="col-lg-5 col-sm-5">
                    <sf:select id="add-rating" class="form-control" name="rating" path="rating">
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
                <label for="add-notes" class="col-lg-2 col-sm-2">
                    Notes:
                </label>
                <div class="col-lg-8 col-sm-8">
                  <input class="form-control" id="add-notes" rows="4" type="text"
                            placeholder="Enter Note"
                  name="notes"/>


                </div>
            </div>

            <div class="col-lg-2 col-sm-2">
                <a href="${pageContext.request.contextPath}/">
                <button type="button" id="add-cancel-button"
                        class="btn btn-danger">
                    Cancel
                </button>
                </a>
            </div>
            <div class="col-lg-2 col-sm-2">
                <a href="${pageContext.request.contextPath}/">
                <button type="submit" id="add-create-dvd-button"
                        class="btn btn-primary">
                    Create Dvd
                </button>
                </a>
            </div>
        </sf:form>
    </div>

</div>

</body>
</html>
