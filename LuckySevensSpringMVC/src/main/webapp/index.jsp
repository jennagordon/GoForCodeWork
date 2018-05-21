<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lucky Sevens</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Lucky Sevens</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                </ul>    
            </div>
            <h2>Lucky Sevens</h2>
            <p>
            <h3>Description</h3>
            Here is a description of the game!
            </p>

            <p>
            <h3>Instructions</h3>
            Here are instructions of how to play
            </p>

            <form method="POST" action="luckySevens">
                <h3>LET'S PLAY!!</h3>
                <p>
                    Enter how much you want to bet:
                    <input type="text" name="betAmount">
                </p>
                <input type="submit" value="Submit">
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

