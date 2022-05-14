<%-- 
    Document   : nuevaCategoria
    Created on : 12 may 2022, 16:16:00
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NuevaCategoria</title>
    </head>
    <body>
        <h1>Nueva Categoria:</h1>
        <form method="POST" action="CategoriaGuardarServlet"> 
            Nueva Categoria: 
            <input type="text" name="nuevaCategoria" value=""/><br/>
            <input type="submit" value="Enviar"/>
        </form>
   
    </body>
</html>
