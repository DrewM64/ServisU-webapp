<%-- 
    Document   : index
    Created on : Mar 12, 2022, 2:09:07 PM
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
        <title>ServisU Car Services</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        
        <c:import url="/include/header.jsp" />
        <section>
            <h2>Car Service for You!</h2>
            <div class="card">
                <img src="img/inspect.jpg" alt="image">
                <div>
                    <p>Service #1</p>
                    <p>Complementary Inspection!</p>
                    <p>A free inspection with every service</p>
                    <p>$0.00</p>
                </div>
            </div>
            <div class="card">
                <img src="img/lift.jpg" alt="image"> 
                <div>
                    <p>Service #2</p>
                    <p>Tire Rotation</p>
                    <p>Keep your wheels spinning.</p>
                    <p>$40.00</p>
                </div>
            </div>
            <div class="card">
                <img src="img/oil.jpg" alt="image">
                <div>
                    <p>Service #3</p>
                    <p>Oil & Filter Change</p>
                    <p>Regular oil and filter change</p>
                    <p>$60.00</p>
                </div>
            </div>
        </section>
        <c:import url="/include/footer.jsp" />