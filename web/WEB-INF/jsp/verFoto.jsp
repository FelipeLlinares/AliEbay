<%-- 
    Document   : verFoto
    Created on : 14 may 2022, 16:43:14
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/jsp/cabeceraFavoritos.jsp" />
        <title>Foto</title>
    </head>
    <body>
        <%
            String url = (String)request.getAttribute("url");
            %>
            
            <img src="<%=url%>">
    </body>
</html>
