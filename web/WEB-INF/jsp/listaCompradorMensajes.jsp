<%-- 
    Document   : listaCompradorMensajes
    Created on : 29 abr. 2022, 13:45:50
    Author     : Cate
--%>

<%@page import="aliebay.dto.ListacompradorDTO"%>
<%@page import="aliebay.dto.MensajeDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensajes Lista Comprador</title>
    </head>
    <%
    ListacompradorDTO lc = (ListacompradorDTO) request.getAttribute("listaComprador");
    List<MensajeDTO> mensajes = (List) request.getAttribute("mensajes");  
    SimpleDateFormat fecha = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
    %>   
    
    <body>
            <jsp:include page="/WEB-INF/jsp/cabeceraMarketing.jsp" />

        <h1>Mensajes <%=lc.getNombre()%></h1>
    <a href="ListaCompradorNuevoEditarMensajeServlet?idLista=<%= lc.getIdLista() %>">Nuevo mensaje</a>
    <br/>
    <br/>
    
    <%
        if (mensajes.isEmpty()){
    %>
    <h2>La Lista de mensajes está vacía</h2>
    <%
        }else{   
    %>
    <table border="1" width="80%">
            <tr>
                <th>Asunto</th>
                <th>Productos</th>
                <th>Descripcion</th>
                <th>Fecha</th>
                <th></th>
            </tr>

        <%
            }for (MensajeDTO m: mensajes) {
                String [] parts = m.getDescripcion().split(";");
                if (parts.length > 2 ){
        %>  
        <tr>

            <td><%= parts[0] == null ? "" : parts[0] %></td>
            <td><%= parts[1] == null ? "" : parts[1]%></td>
            <td> <%= parts[2] == null ? "" : parts[2] %></td>
        <%
            } else{
        %>    
            <td><%= "Sin asunto" %></td>
            <td><%= "Sin productos" %></td>
            <td> <%= m.getDescripcion() %></td>
        <%
            }
        %>
            <td> <%= fecha.format(m.getFecha())%></td>
            <td><a href="ListaCompradorNuevoEditarMensajeServlet?id=<%= m.getIdMensaje() %>&idLista=<%= lc.getIdLista() %>">Editar</a></td>
            <td><a href="ListaCompradorBorrarMensajeServlet?id=<%= m.getIdMensaje() %>&idLista=<%= lc.getIdLista() %>">Borrar</a></td>
        </tr>
        <%
            }
        %>            
        </table>
    </body>
</html>
