<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/19/2018
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Company Contacts: Login</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Company Contacts</h1>
    <hr/>
    <h2>Login Page</h2>

    <c:if test="${param.login_error == 1}">
        <h3>Wrong id or password!</h3>
    </c:if>
    <form class="form-horizontal"
          role="form"
          method="post"
          action="j_spring_security_check">
        <div class="form-group">
            <label for="j_username"
                   class="col-md-4 control-label">Username:</label>
            <div class="col-md-8">
                <input type="text"
                       class="form-control"
                       name="j_username"
                       placeholder="Username"/>
            </div>
        </div>
        <div class="form-group">
            <label for="j_password"
                   class="col-md-4 control-label">Password:</label>
            <div class="col-md-8">
                <input type="password"
                       class="form-control"
                       name="j_password"
                       placeholder="Password"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <input type="submit"
                       class="btn btn-default"
                       id="search-button"
                       value="Sign In"/>
            </div>
        </div>
    </form>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
