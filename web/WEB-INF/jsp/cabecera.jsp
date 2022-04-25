<%-- 
    Document   : cabecera
    Created on : 20 abr. 2022, 21:11:57
    Author     : Cate
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
        <td><a href="AdminServlet">Listado de clientes</a></td>
        <td><a href="LogoutServlet">Salir</a></td>
    </tr>
</table>