<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 4/18/2018
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello Security: Content</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Hello Security</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/home">
                    Home
                </a>
            </li>
            <li role="presentation" class="active">
                <a href="${pageContext.request.contextPath}/content">
                    Content (must have the user role)
                </a>
            </li>
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/admin">
                    Admin (must have the admin role)
                </a>
            </li>
        </ul>
    </div>
    <h2>Content Page</h2>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h4>Hello : ${pageContext.request.userPrincipal.name}
            | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
        </h4>
    </c:if>

    <p>
        Only users with the USER role can see this page.
    </p>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p>
            This is only visible to users who also have the ADMIN role.
        </p>
    </sec:authorize>


</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
