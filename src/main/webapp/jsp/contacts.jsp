<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0260583
  Date: 3/15/2018
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Company Contacts</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Company Contacts</h1>
    <hr>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
        </ul>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
    </c:if>
    <div class="row">
        <div class="col-lg-6 col-sm-6">
            <h2>My Contacts</h2>
            <table id="contactTable" class="table table-hover">
                <tr>
                    <th width="40%">Contact Name</th>
                    <th width="30%">Company</th>
                    <th width="15%"></th>
                    <th width="15%"></th>
                </tr>
                <c:forEach var="currentContact" items="${contactList}">
                    <tr>
                        <td>
                            <a href="displayContactDetails?contactId=${currentContact.contactId}">
                            <c:out value="${currentContact.firstName}"/>
                            <c:out value="${currentContact.lastName}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${currentContact.company}"/>
                        </td>
                        <td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="displayEditContactForm?contactId=${currentContact.contactId}">
                            Edit
                            </a>
                            </sec:authorize>
                        </td>
                        <td>
                            <!-- made the delete a link that will bring the user to the created link -->
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="deleteContact?contactId=${currentContact.contactId}">
                            Delete
                            </a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div> <!-- end col div -->
        <div class="col-md-6">
<sec:authorize access="hasRole('ROLE_ADMIN')">
            <h2>Add New Contact</h2>
            <form class="form-horizontal"
                  role="form" method="POST"
                  action="createContact">
                <div class="form-group">
                    <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                    <div class="col-md-8">
                        <input id="add-first-name" type="text" class="form-control" name="firstName"
                               placeholder="First Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                    <div class="col-md-8">
                        <input id="add-last-name" type="text" class="form-control" name="lastName"
                               placeholder="Last Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-company" class="col-md-4 control-label">Company:</label>
                    <div class="col-md-8">
                        <input id="add-company" type="text" class="form-control" name="company" placeholder="Company"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-email" class="col-md-4 control-label">Email:</label>
                    <div class="col-md-8">
                        <input id="add-email" type="email" class="form-control" name="email" placeholder="Email"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                    <div class="col-md-8">
                        <input id="add-phone" type="tel" class="form-control" name="phone" placeholder="Phone"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Create Contact"/>
                    </div>
                </div>
            </form>
</sec:authorize>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
