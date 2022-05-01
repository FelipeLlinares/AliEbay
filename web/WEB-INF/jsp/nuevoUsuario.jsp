<%-- 
    Document   : nuevoUsuario
    Created on : 8 abr. 2022, 13:56:02
    Author     : Cate
--%>

<%@page import="aliebay.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar usuario</title>
    </head>
    <%
        
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        
        String h = "";
        String m = "";
        String tipoUsuario  = (String)request.getAttribute("tipoUsuario");
        if (usuario != null){
            if (usuario.getSexo().equalsIgnoreCase("Hombre")){
                h = "checked";
            } else {
                m = "checked";
            }
        }
    %>
    
    <body>
        <h1>Datos usuario</h1>
        <form action="UsuarioGuardarServlet" method="POST">        
        <input type="hidden" name="id" value="<%= usuario == null ? "" : usuario.getIdUsuario() %>"/>
        Nombre: <input type="text" name="nombre" value="<%= usuario == null ? "" : usuario.getNombre()%>" /> <br/>
        Apellidos: <input type="text" name="apellidos" value="<%= usuario == null ? "" : usuario.getApellidos() %>" /> <br/>
        Domicilio: <input type="text" name="domicilio" value="<%= usuario == null ? "" : usuario.getDomicilio()%>" /> <br/>
        Ciudad: <input type="text" name="ciudad" value="<%= usuario == null ? "" : usuario.getCiudadResidencia()%>" /> <br/>
        Edad: <input type="text" name="edad" value="<%= usuario == null ? "" : usuario.getEdad()%>" /> <br/>  
        Sexo:
        <input type="radio" name="sexo" value="Hombre" <%= h %> />Hombre
        <input type="radio" name="sexo" value="Mujer" <%= m %>  /> Mujer
        </br>
        
        <%
            if(usuario == null && tipoUsuario == null){
        %>
        
        Tipo Usuario:
        <input type="radio" name="tipoUsuario" value="comprador"/>Comprador
        <input type="radio" name="tipoUsuario" value="vendedor"/> Vendedor
        <br/>
        
        <%
            }else if(tipoUsuario != null){
              
        %>
            <input type="hidden" name="admin" value="1"/>
            <input type="hidden" name="tipoUsuario" value="<%=tipoUsuario %>"/>
        
        <%
            }
        %>
        
        Usuario: <input type="text" name="usuario"/> <br/>
        Contraseña: <input type="password" name="password"/> <br/>
        <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
