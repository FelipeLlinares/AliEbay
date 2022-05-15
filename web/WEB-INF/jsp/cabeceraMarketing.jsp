<%-- 
    Document   : cabeceraMarketing
    Created on : 15 may. 2022, 12:46:41
    Author     : Cate
--%>


<%@page import="aliebay.dto.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
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
        <td><a href="MarketingServlet">Inicio</a></td>
        <td><a href="LogoutServlet">Salir</a></td>
    </tr>
</table>
        <html>
          <body>
            <h2 style="color:red"><%= strError%><br/></h2>
        </body>  
        </html>
