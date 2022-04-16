<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ServisU</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>    
</head>

<body>
    <c:import url="/include/header.jsp" />
    <h1>Thanks for joining our email list</h1>

    <p>Here is the information that you entered:</p>

    <label>Email:</label>
    <span>${user.email}</span><br>
    <label>First Name:</label>
    <span>${user.firstName}</span><br>
    <label>Last Name:</label>
    <span>${user.lastName}</span><br>

    <p>To enter another email address, click on the Back 
    button in your browser or the Return button shown 
    below.</p>

    <form action="user" method="post">
        <input type="hidden" name="action" value="view">
        <input type="submit" value="Return">
    </form>

    <p><i>${errorMessage}</i></p>
    
<c:import url="/include/footer.jsp" />