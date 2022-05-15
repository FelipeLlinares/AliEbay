<%-- 
    Document   : mensajeNuevo
    Created on : 29 abr. 2022, 12:01:44
    Author     : Cate
--%>

<%@page import="aliebay.dto.MensajeDTO"%>
<%@page import="aliebay.dto.CompradorDTO"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.dto.ListacompradorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje Nuevo</title>
    </head>
    <body>
    <jsp:include page="/WEB-INF/jsp/cabeceraMarketing.jsp" />
            <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje Nuevo</title>
    </head>
       <%
        ListacompradorDTO listaComprador = (ListacompradorDTO) request.getAttribute("listaComprador");
        MensajeDTO mensaje = (MensajeDTO) request.getAttribute("mensaje");
        %>
    <body>
        <h1>Mensaje nuevo para la lista:  <%= listaComprador.getNombre() %></h1>
        <form action="ListaCompradorGuardarMensajeServlet" method="POST">      
        <input type="hidden" name="id" value="<%= mensaje == null ? "" : mensaje.getIdMensaje() %>"/>
        <input type="hidden" name="idLista" value="<%= listaComprador == null ? "" : listaComprador.getIdLista()%>"/>
        <table>
       <%  
           if (mensaje != null){
               String [] parts = mensaje.getDescripcion().split(";");
           if (parts != null && parts.length > 2 ){
       %>  
            <tr>
                <td> Asunto: </td>    
                <td><input type="text" name="asunto" value="<%= parts[0] %>" /></td>
            </tr> 
            <tr>
                <td>Productos:</td>
                <td><input type="text" name="productos" value="<%= parts[1] %>" /></td>
            </tr>
            <tr>
                <td>Descripcion del mensaje:</td>
                <td><textarea name="description" rows="4" cols="50"><%= parts[2] %></textarea></td>
            </tr>
            <% 
        }
                }else{
            %>
            <tr>
                <td> Asunto: </td>    
                <td><input type="text" name="asunto" value="" /></td>
            </tr> 
            <tr>
                <td>Productos:</td>
                <td><input type="text" name="productos" value="" /></td>
            </tr>
            <tr>
                <td>Descripcion del mensaje:</td>
                <td><textarea name="description" rows="4" cols="50"><%= mensaje == null ? "" : mensaje.getDescripcion() %></textarea></td>
            </tr>
            <%
                }
            %>
        </table>
        
        <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
