<%-- 
    Document   : userPage
    Created on : Mar 20, 2022, 4:39:28 PM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServisU | Welcome</title>
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        
        <c:import url="/include/header.jsp" />
        <section>
            <h1>Welcome, ${user.username}!</h1>
        </section>
        <c:import url="/include/footer.jsp" />
