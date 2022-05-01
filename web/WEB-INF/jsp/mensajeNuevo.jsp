<%-- 
    Document   : mensajeNuevo
    Created on : 29 abr. 2022, 12:01:44
    Author     : Cate
--%>

<%@page import="aliebay.entity.Mensaje"%>
<%@page import="aliebay.entity.Comprador"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.entity.Listacomprador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje Nuevo</title>
    </head>
    <body>
            <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje Nuevo</title>
    </head>
       <%
        Listacomprador listaComprador = (Listacomprador) request.getAttribute("listaComprador");
        Mensaje mensaje = (Mensaje) request.getAttribute("mensaje");
        %>
    <body>
        <h1>Mensaje nuevo para la lista:  <%= listaComprador.getNombre() %></h1>
        <form action="ListaCompradorGuardarMensajeServlet" method="POST">      
        <input type="hidden" name="id" value="<%= mensaje == null ? "" : mensaje.getMensajePK().getId() %>"/>
        <input type="hidden" name="idLista" value="<%= listaComprador == null ? "" : listaComprador.getIdLista()%>"/>
        Descripcion del mensaje:<br/>
        <textarea name="description" rows="4" cols="50"></textarea><br/>
        <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
