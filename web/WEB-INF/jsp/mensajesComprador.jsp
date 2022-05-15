<%-- 
    Document   : mensajesComprador
    Created on : 15 may. 2022, 13:21:06
    Author     : Cate
--%>

<%@page import="aliebay.dto.MensajeDTO"%>
<%@page import="aliebay.dto.ListacompradorDTO"%>
<%@page import="aliebay.dto.CompradorDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <jsp:include page="/WEB-INF/jsp/cabeceraComprador.jsp" />
        <h1>Lista de mensajes</h1>
        <%
            CompradorDTO comprador = (CompradorDTO) request.getAttribute("comprador");
            SimpleDateFormat fecha = new SimpleDateFormat  ("dd/MM/yyyy HH:mm:ss");
            if (comprador.getListacompradorList().isEmpty()){
         %>
         <h2>No pertenece a ninguna lista de compradores</h2>
          <%
            }else{
    %>
                <table border="1">
            <tr>
                <th>Asunto</th>
                <th>Productos</th>
                <th>Descripcion</th>
                <th>Fecha</th>
            </tr>
            <%
            for (ListacompradorDTO lc : comprador.getListacompradorList()){
                if (!lc.getMensajeList().isEmpty()){
                for (MensajeDTO m : lc.getMensajeList()) {            
              String [] parts = m.getDescripcion().split(";");
              if (parts != null && parts.length > 2 ){
        %>  
        <tr>
            <td><%= parts[0] %></td>
            <td><%= parts[1]%></td>
            <td> <%= parts[2] %></td>
        <%
            } else{
        %>    
            
            <tr>
            <td><%= "Sin asunto" %></td>
            <td><%= "Sin productos" %></td>
            <td> <%= m.getDescripcion() %></td>
        <%
            }
        %>
            <td><%= fecha.format(m.getFecha()) %></td>
            </tr>    
        <%              
                         }
                    }
                }
            }
        %>
        </table>
    </body>
</html>
