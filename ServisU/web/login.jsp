<%-- 
    Document   : login
    Created on : Mar 12, 2022, 1:59:32 PM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>ServisU | Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        
        <c:import url="/include/header.jsp" />
        <section>
            <h2>Login</h2>
            <p class="message">${message}</p>
            <form action="login" method="post">
                <input type="hidden" name="action" value="logindb">
                <label for="username">Username:</label><br>
                    <input type="text" id="username" name="username" required>
                    <br>
                <label for="password">Password:</label><br>
                    <input type="password" id="password" name="password" required>
                <input type="submit" value="Submit">
            </form>
        </section>
        <c:import url="/include/footer.jsp" />
