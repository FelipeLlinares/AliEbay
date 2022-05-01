<%-- 
    Document   : listaCompradores
    Created on : 25 abr. 2022, 9:31:57
    Author     : Cate
--%>

<%@page import="aliebay.entity.Listacomprador"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marketing</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
        <h1>Lista de compradores</h1>
        <table border="1" width="80%">
            <tr>
                <th>Nombre</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        <a href="ListaCompradorNuevoEditarServlet">Crear nueva lista comprador....</a>
        <%
            List<Listacomprador> listaCompradores = (List)request.getAttribute("listaCompradores");
            for (Listacomprador lc: listaCompradores) {
        %>  
        <tr>
            <td><%= lc.getNombre()%></td>
            <td><a href="ListaCompradorMensajeServlet?id=<%= lc.getIdLista()%>">Ver Mensajes</td>
            <td><a href="ListaCompradorNuevoEditarServlet?id=<%= lc.getIdLista()%>">Editar</a></td>
            <td><a href="ListaCompradorBorrarServlet?id=<%= lc.getIdLista()%>">Borrar</a></td>
        </tr>
        <%
            }
        %>            
        </table>
    </body>
</html>
