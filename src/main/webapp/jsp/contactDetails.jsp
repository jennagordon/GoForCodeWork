<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 3/15/2018
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company Contacts</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Contact Details</h1>
    <hr>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
        </ul>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>Hello : ${pageContext.request.userPrincipal.name}
            | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
        </p>
    </c:if>

    <p>
        Name: <c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/>
    </p>
    <p>
        Company: <c:out value="${contact.company}"/>
    </p>
    <p>
        Phone: <c:out value="${contact.phone}"/>
    </p>
    <p>
        Email: <c:out value="${contact.email}"/>
    </p>
</div>

</body>
</html>
