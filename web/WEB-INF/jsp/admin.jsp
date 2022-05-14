<%-- 
    Document   : lista
    Created on : 7 abr. 2022, 15:42:22
    Author     : Cate
--%>

<%@page import="aliebay.entity.Marketing"%>
<%@page import="aliebay.entity.Vendedor"%>
<%@page import="aliebay.entity.Comprador"%>
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
        <h2>Compradores: </h2>
        <table border="1" width="80%">
            <tr>
                <th>ID</th>
                <th>Nombre</th>            
                <th>Apellidos</th>                             
                <th>Domicilio</th>
                <th>Ciudad</th>
                <th>Edad</th>
                <th>Sexo</th>
                <th></th><!-- Borrar -->
                <th></th><!-- Editar -->
                <th></th><!-- Productos -->
            </tr>
        <%
            List<Comprador> compradores = (List)request.getAttribute("compradores");
            for (Comprador c: compradores) { 
                Usuario u = c.getUsuario();
        %>    
        <tr>
            <td><%= u.getIdUsuario()%></td>
            <td><%= u.getNombre() %></td>            
            <td><%= u.getApellidos() %></td>                     
            <td><%= u.getDomicilio() %></td>   
            <td><%= u.getCiudadResidencia()%></td> 
            <td><%= u.getEdad()%></td> 
            <td><%= u.getSexo()%></td>
            <td><a href="UsuarioBorrarServlet?id=<%= u.getIdUsuario()%>">Borrar</a></td>
            <td><a href="UsuarioNuevoEditarServlet?id=<%= u.getIdUsuario()%>">Editar</a></td>
            <td><a href="ProductosCompradorServlet?id=<%=u.getIdUsuario()%>">Productos</a></td>
        </tr>
        
        <%
            }
        %>
        </table>
        <a href="UsuarioNuevoEditarServlet?tipoUsuario=comprador">Crear Nuevo Comprador</a><br/>
        
        <h2>Vendedores: </h2>
        <table border="1" width="80%">
            <tr>
                <th>ID</th>
                <th>Nombre</th>                
                <th>Apellidos</th>                                
                <th>Domicilio</th>  
                <th>Ciudad</th> 
                <th>Edad</th> 
                <th>Sexo</th>
                <th></th><!-- Borrar -->
                <th></th><!-- Editar -->
                <th></th><!-- Productos -->
            </tr>
        <%
            List<Vendedor> vendedores = (List)request.getAttribute("vendedores");
            for (Vendedor v: vendedores) {
                Usuario u = v.getUsuario();
        %>    
        <tr>
           <td><%= u.getIdUsuario()%></td>
           <td><%= u.getNombre() %></td>            
           <td><%= u.getApellidos() %></td>                     
           <td><%= u.getDomicilio() %></td>   
           <td><%= u.getCiudadResidencia()%></td> 
           <td><%= u.getEdad()%></td> 
           <td><%= u.getSexo()%></td>
           <td><a href="UsuarioBorrarServlet?id=<%= u.getIdUsuario()%>">Borrar</a></td>
           <td><a href="UsuarioNuevoEditarServlet?id=<%= u.getIdUsuario()%>">Editar</a></td>
           <td><a href="MostrarProductosServlet?id=<%=u.getIdUsuario()%>">Productos</a></td>
        </tr>
        
        <%
            }
        %>
        </table>
        <a href="UsuarioNuevoEditarServlet?tipoUsuario=vendedor">Crear Nuevo Vendedor</a><br/>
        <h2>Marketing: </h2>
        <table border="1" width="80%">
            <tr>
                <th>ID</th>
                <th>Nombre</th>                
                <th>Apellidos</th>                                
                <th>Domicilio</th>  
                <th>Ciudad</th> 
                <th>Edad</th> 
                <th>Sexo</th>
                <th></th><!-- Borrar -->
                <th></th><!-- Editar -->
            </tr>
        <%
            List<Marketing> marketings = (List)request.getAttribute("marketings");
            for (Marketing m: marketings) {
                Usuario u = m.getUsuario();
        %>    
        <tr>
           <td><%= u.getIdUsuario()%></td>
           <td><%= u.getNombre() %></td>            
           <td><%= u.getApellidos() %></td>                     
           <td><%= u.getDomicilio() %></td>   
           <td><%= u.getCiudadResidencia()%></td> 
           <td><%= u.getEdad()%></td> 
           <td><%= u.getSexo()%></td>
           <td><a href="UsuarioBorrarServlet?id=<%= u.getIdUsuario()%>">Borrar</a></td>
           <td><a href="UsuarioNuevoEditarServlet?id=<%= u.getIdUsuario()%>">Editar</a></td>
        </tr>
        
        <%
            }
        %>
        </table>
        <a href="UsuarioNuevoEditarServlet?tipoUsuario=marketing">Crear Nuevo Marketing</a>
        <br/>
        <br/>
        
        <a href="GestionarCategoriasServlet">Categorias de productos</a>
    </body>
</html>
