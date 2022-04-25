<%-- 
    Document   : productos
    Created on : 25 abr 2022, 11:57:30
    Author     : felip
--%>

<%@page import="java.util.List"%>
<%@page import="aliebay.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
        <h1>Listado de productos:</h1>
        <table border="1" width="80%">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>            
                <th>Categoria</th>                             
            </tr>
        <%
            List<Producto> productos = (List)request.getAttribute("productos");
            for (Producto p: productos) {
        %>    
        <tr>
            <td><%= p.getTitulo()%></td>
            <td><%= p.getDescripcion() %></td>            
            <td><%= p.getCategoria() %></td>                      
        </tr>
        
        <%
            }
        %>
        </table>
    </body>
</html>
