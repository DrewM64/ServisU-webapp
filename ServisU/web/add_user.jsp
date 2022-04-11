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
        <h1>Add Tech Support</h1>
        <p><i>${message}</i></p>
    <form action="admin" method="post">
        <input type="hidden" name="action" value="add">        
        <label>Email:</label>
        <input type="email" name="email" value="${tech.email}" 
               required><br>
        <label>First Name:</label>
        <input type="text" name="firstName" value="${tech.firstName}" 
               required><br>
        <label>Last Name:</label>
        <input type="text" name="lastName" value="${tech.lastName}"  
               required><br>
        <label>Phone Number:</label>
        <input type="text" name="phoneNumber" value="${tech.phone}"  
               required><br>  
        <label>&nbsp;</label>
        <input type="submit" value="Submit">
    </form>
    <c:import url="/includes/footer.jsp" />
