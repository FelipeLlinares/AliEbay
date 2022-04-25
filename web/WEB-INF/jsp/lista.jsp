<%-- 
    Document   : lista
    Created on : 7 abr. 2022, 15:42:22
    Author     : Cate
--%>

<%@page import="aliebay.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de usuarios</title>
    </head>
    <body>
       <jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
        <h1>Listado de usuarios</h1>
        <table border="1" width="80%">
            <tr>
                <th>ID</th>
                <th>Nombre</th>                
                <th>Apellidos</th>                                
                <th>Domicilio</th>  
                <th>Ciudad</th> 
                <th>Edad</th> 
                <th>Sexo</th> 
            </tr>
        <%
            List<Usuario> usuarios = (List)request.getAttribute("usuarios");
            for (Usuario u: usuarios) {
        %>    
        <tr>
            <td><%= u.getIdUsuario()%></td>
            <td><%= u.getNombre() %></td>            
            <td><%= u.getApellidos() %></td>                     
           <td><%= u.getDomicilio() %></td>   
           <td><%= u.getCiudadResidencia()%></td> 
           <td><%= u.getEdad()%></td> 
           <td><%= u.getSexo()%></td>
           <td><a href="UsuarioBorrarServlet?id=<%= u.getIdUsuario()%>">Borrar</a><td>
           <td><a href="UsuarioNuevoEditarServlet?id=<%= u.getIdUsuario()%>">Editar</a><td>
        </tr>
        
        <%
            }
        %>
        </table>
        <a href="UsuarioNuevoEditarServlet">Crear Nuevo Usuario</a>
    </body>
</html>
