<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="POST" action="ShoppingList">
            <label>Username: </label>
            <input type="text" name="username">
            
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register name">
            
        </form>
        
        <c:if test="${loginError == true}">
            <p>Invalid login.</p>
        </c:if>
        <c:if test="${logout == true}">
            <p>You have successfully logged out.</p>
        </c:if>
        
    </body>
</html>
