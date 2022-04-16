<%-- 
    Document   : support
    Created on : Feb 27, 2022, 9:50:45 PM
    Author     : Andrew Montgomery
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>View Users</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        <c:import url="/include/header.jsp" />
        <section>
            <h1>Users Table</h1>
            <form name="viewButton" method="post" action="user">
                <input type="hidden" name="action" value="view">
                <input type="submit" value="Load Database">
            </form>
            <table>
                <tr>
                    <th colspan="6">Users</th>
                </tr>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th style="width: 1px"/>
                </tr>
               
                <!--
                Populates table with data from tech_support DB
                techSupportList is an ArrayList of that data
                Submitting editButton form sends that row's tech info to AdminServlet
                to route it to edit_tech_support.jsp for editing
                -->
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.username} </td>
                        <td>${user.password}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>
                            <form name="editButton" method="post" action="user">
                                <input type="hidden" name="action" value="editButton">
                                <input type="hidden" name="username" value="${user.username}">
                                <input type="submit" value="Edit">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6"><a href="add_user.jsp">Add New User</a></td>
                </tr>
            </table>
        </section>
        <c:import url="/include/footer.jsp" />