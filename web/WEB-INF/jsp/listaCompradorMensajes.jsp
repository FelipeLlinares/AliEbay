<%-- 
    Document   : listaCompradorMensajes
    Created on : 29 abr. 2022, 13:45:50
    Author     : Cate
--%>

<%@page import="aliebay.entity.Mensaje"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.entity.Listacomprador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensajes Lista Comprador</title>
    </head>
    <%
    Listacomprador lc = (Listacomprador) request.getAttribute("listaComprador");
    List<Mensaje> mensajes = (List) request.getAttribute("mensajes");   
    %>   
    
    <body>
        <h1>Mensajes <%=lc.getNombre()%></h1>
<table border="1" width="80%">
            <tr>
                <th>Asunto</th>
                <th>Descripcion</th>
                <th>Fecha</th>
                <th></th>
            </tr>
        <a href="ListaCompradorNuevoEditarMensajeServlet?idLista=<%= lc.getIdLista() %>">Nuevo mensaje</a>
        <%
            for (Mensaje m: mensajes) {
        %>  
        <tr>

            <td><%= m.getMensajePK().getId()%></td>
            <td> <%= m.getDescripcion() %> </td>
            <td> <%= m.getFecha()%></td>
            <td><a href="ListaCompradorNuevoEditarMensajeServlet?id=<%= m.getMensajePK().getId() %>&idLista=<%= lc.getIdLista() %>">Editar</a></td>
            <td><a href="ListaCompradorBorrarMensajeServlet?id=<%= m.getMensajePK().getId() %>&idLista=<%= lc.getIdLista() %>">Borrar</a></td>
        </tr>
        <%
            }
        %>            
        </table>
    </body>
</html>
