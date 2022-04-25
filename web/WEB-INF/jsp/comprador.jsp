<%-- 
    Document   : comprador
    Created on : 25 abr 2022, 11:09:26
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>  
    <body>
        <jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
        <h1>Comprador Servlet</h1>
        <%= strError%><br/>
    </body>
</html>
