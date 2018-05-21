<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lucky Sevens</title>
</head>
<body>
<h2>Lucky Sevens</h2>
<p>
    <h3>Description</h3>
Here is a description of the game!
</p>

<p>
    <h3>Instructions</h3>
Here are instructions of how to play
</p>

<form method="POST" action="LuckySevensServlet">
    <h3>LET'S PLAY!!</h3>
    <p>
        Enter how much you want to bet:
        <input type="text" name="betAmount">
    </p>
    <input type="submit" value="Submit">
</form>
</body>
</html>
