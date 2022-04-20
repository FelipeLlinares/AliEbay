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
        
    %>
    <body>
        <h1>Datos usuario</h1>
        <form action="UsuarioGuardarServlet" method="POST">        
        <input type="hidden" name="id" value="<%= usuario == null ? "" : usuario.getIdUsuario() %>"/>
        Nombre: <input type="text" name="nombre" value="<%= usuario == null ? "" : usuario.getNombre()%>" /> </br>
        Apellidos: <input type="text" name="apellidos" value="<%= usuario == null ? "" : usuario.getApellidos() %>" /> </br>
        Domicilio: <input type="text" name="domicilio" value="<%= usuario == null ? "" : usuario.getDomicilio()%>" /> </br>
        Ciudad: <input type="text" name="ciudad" value="<%= usuario == null ? "" : usuario.getCiudadResidencia()%>" /> </br>
        Edad: <input type="text" name="edad" value="<%= usuario == null ? "" : usuario.getEdad()%>" /> </br>
        Sexo:
        <%
            if (usuario != null){
                
                if (usuario.getSexo().equalsIgnoreCase("Hombre")){
                    h = "checked";
                } else {
                    m = "checked";
                }
            }
        %>
        <input type="radio" name="sexo" value="Hombre" <%= h %> />Hombre
        <input type="radio" name="sexo" value="Mujer" <%= m %>  /> Mujer
        </br>
        <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
