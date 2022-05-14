<%-- 
    Document   : filtradoProductos
    Created on : 14 may. 2022, 14:24:58
    Author     : encac
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

<form action="CompradorGuardarPujaServlet" method="POST">
    <table width="80%">
        <tr width="80%">
            <td>Buscar por nombre: <input type="text" id="puja" name="filtro"></td>
            <td>Session ID, <%= user.getIdUsuario() %></td>
            <td><a href="CompradorServlet">Volver</a></td>
        </tr>
    </table>
</form>

<html>
    <body>
    </body>  
</html>
