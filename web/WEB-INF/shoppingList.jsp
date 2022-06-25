<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}</p>
        <a href="ShoppingList?action=logout">Logout</a>
        
        <!--Form to add items to the list-->
            <h2>List</h2>
            <form method="POST" action="ShoppingList">
                <label>Add item: </label>
                <input type="text"  name="itemName">
                
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add">
            </form>

            <c:if test="${inputError == true}">
                <p>Invalid entry.</p>
            </c:if>
        
        <!--Form to show/delete added items-->
            <c:if test="${added == true}">
                <form method="POST" action="ShoppingList">
                    <table>
                        <tr>
                            <c:forEach items="${list}" var="listItem">
                                <tr>
                                    <td><input type="radio" name="itemButton" value="${listItem}"></td>
                                    <td>${listItem}</td>
                                </tr>
                            </c:forEach>
                        </tr>
                    </table>

                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Delete">
                </form>

                <c:if test="${deleteError == true}">
                    <p>Invalid entry.</p>
                </c:if>
            </c:if>
            
    </body>
</html>
