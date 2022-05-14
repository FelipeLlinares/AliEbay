<%-- 
    Document   : cabeceraFavoritos
    Created on : 13 may. 2022, 19:59:09
    Author     : Enrique Cañadas Cobo
--%>

<%@page import="aliebay.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user == null){
        response.sendRedirect(request.getContextPath());
    }     
%>


<table width="80%">
    <tr width="80%">
        <td>Bievenido, <%= user.getNombre()%></td>
        <td>Session ID, <%= user.getIdUsuario() %></td>
        <td><a href="CompradorServlet">Volver</a></td>
    </tr>
</table>
<html>
    <body>
    </body>   
</html>
