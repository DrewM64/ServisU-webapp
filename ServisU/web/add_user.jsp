<%-- 
    Document   : add_tech_support
    Created on : Mar 31, 2022, 1:18:05 PM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Tech Support</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        <!--
        Allows user to add (INSERT) tech support information. Validation for 
        required fields and phone number being numbers handled on servlet
        Sends to AdminServlet with action "add"
        -->
        <h1>Add User</h1>
        <p class="message"><i>${message}</i></p>
    <form action="user" method="post">
        <input type="hidden" name="action" value="add">        
        <label>Username:</label>
        <input type="text" name="username" value="${user.username}" 
               required><br>
        <label>First Name:</label>
        <input type="text" name="firstName" value="${userfirstName}" 
               required><br>
        <label>Last Name:</label>
        <input type="text" name="lastName" value="${user.lastName}"  
               required><br>
        <label>Create Password:</label>
        <input type="text" name="password" value="${user.password}"  
               required><br>  
        <label>&nbsp;</label>
        <input type="submit" value="Submit">
    </form>
    <c:import url="/include/footer.jsp" />
