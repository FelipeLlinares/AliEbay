<%-- 
    Document   : login.jsp
    Created on : 16 abr. 2022, 19:59:57
    Author     : Cate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenidos al sistema</title>
    </head>
    <body>
        <h1>Login</h1>
        <%
            String strError = (String)request.getAttribute("error");
            if (strError != null){
        %>  
            <h3 style="color:red"><%= strError%><br/></h3>
        <%
            }
        %>
        <form id = "form" method="POST" action="LoginServlet">
            <table>
                <tr>
                    <td>Usuario:</td>
                    <td><input type="text" name="usuario" value="" /></td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type="password" name="clave" value=""/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Enviar"/></td>
                </tr>
            </table>
            <br/>
            ¿No tienes una cuenta? <a href="UsuarioNuevoEditarServlet">Regístrate</a>
        </form>
    </body>
</html>
