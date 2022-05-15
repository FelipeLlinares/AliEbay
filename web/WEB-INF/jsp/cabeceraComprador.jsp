<%-- 
    Document   : cabeceraComprador
    Created on : 13 may. 2022, 13:00:24
    Author     : Enrique Cañadas Cobo
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

        <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
        %>  

<table width="80%">
    <tr width="80%">
        <td>Bievenido, <%= user.getNombre()%></td>
        <td>Session ID, <%= user.getIdUsuario() %></td>
        <td><a href="FavoritoServlet">Favoritos</a></td>
        <td><a href="ProductosCompradorServlet?id=<%= user.getIdUsuario()%>">Productos comprados</a></td>
        <td><a href="LogoutServlet">Salir</a></td>
    </tr>
</table>
        <html>
          <body>
            <h2 style="color:red"><%= strError%><br/></h2>
        </body>  
        </html>
