<%-- 
    Document   : footer
    Created on : Mar 12, 2022, 12:30:30 PM
    Author     : daniel
--%>

<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
<%  
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
%>
<footer>&copy; Copyright <%= currentYear %> Andrew Montgomery</footer>
</body>
</html>
