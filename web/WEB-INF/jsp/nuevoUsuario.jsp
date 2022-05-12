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
        <table>
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" value="<%= usuario == null ? "" : usuario.getNombre()%>" /></td>
            </tr>
            <tr>
                <td>Apellidos:</td>
                <td><input type="text" name="apellidos" value="<%= usuario == null ? "" : usuario.getApellidos() %>" /></td>
            </tr><!-- comment -->
            <tr>
                <td>Domicilio:</td>
                <td><input type="text" name="domicilio" value="<%= usuario == null ? "" : usuario.getDomicilio()%>" /></td>
            </tr>
            <tr>
                <td>Ciudad:</td>
                <td><input type="text" name="ciudad" value="<%= usuario == null ? "" : usuario.getCiudadResidencia()%>" /></td>
            </tr>
            <tr>
                <td>Edad:</td>
                <td><input type="text" name="edad" value="<%= usuario == null ? "" : usuario.getEdad()%>" /></td>
            </tr><!-- comment -->
            <tr>
                <td>Sexo:</td>
                <td>
                    <input type="radio" name="sexo" value="Hombre" <%= h %> />Hombre
                    <input type="radio" name="sexo" value="Mujer" <%= m %>  /> Mujer
                </td>
            </tr>
        
        <%
            if(usuario == null && tipoUsuario == null){
        %>
            <tr>
                <td>Tipo Usuario:</td>
                <td>
                    <input type="radio" name="tipoUsuario" value="comprador"/>Comprador
                    <input type="radio" name="tipoUsuario" value="vendedor"/> Vendedor
                </td>
            </tr>
        <%
            }else if(tipoUsuario != null){
              
        %>
            <input type="hidden" name="admin" value="1"/>
            <input type="hidden" name="tipoUsuario" value="<%=tipoUsuario %>"/>   
        <%
            }
        %>
            <tr>
                <td>Usuario:</td>
                <td><input type="text" name="usuario" value="<%= usuario == null ? "" : usuario.getUserName() %>"/></td>
            </tr><!-- comment -->
            <tr>
                <td>Contraseña:</td>
                <td><input type="password" name="password" value="<%= usuario == null ? "" : usuario.getPassword() %>"/></td>
            </tr>
         
            <tr>
                <td><input type="submit" value="Enviar" /></td>
            </tr>
        </table>
        </form>
    </body>
</html>
