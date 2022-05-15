<%-- 
    Document   : categoria
    Created on : 1 may 2022, 12:20:55
    Author     : felip
--%>
<%@page import="java.util.List"%>
<%@page import="aliebay.dto.CategoriaDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionar categorias</title>
    </head>
    <body>
       <jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
        <h1>Listado de categorias</h1>
        <table border="1" width="80%">
            <tr>
                <th>Nombre</th>            
                <th></th><!-- Borrar -->
                <th></th><!-- Mostrar Productos -->
            </tr>
        <%
            List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");
            for (CategoriaDTO c: categorias) {
        %>    
        <tr>
            <td><%= c.getIdCategoria() %></td>            
           <td><a href="CategoriaBorrarServlet?id=<%= c.getIdCategoria()%>">Borrar</a></td>
           <td><a href="ProductosPorCartegoriaServlet?id=<%= c.getIdCategoria()%>">Mostrar Productos</a></td>
        </tr>
        
        <%
            }
        %>
        </table>
        <a href="CategoriaNuevoEditarServlet">Nueva categoria</a>
    </body>
</html>
