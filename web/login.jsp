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
        <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>  
    <body>
        <h1>Login</h1>
        <%= strError%><br/>
        <form method="POST" action="LoginServlet">
            Usuario: <input type="text" name="usuario" value="" /><br/>
            Clave: <input type="password" name="clave" value="" /><br/>           
            <input type="submit" value="Enviar"/><br/>  
            ¿No tienes una cuenta? <a href="UsuarioNuevoEditarServlet" >Regístrate</a>
        </form>
    </body>
</html>
