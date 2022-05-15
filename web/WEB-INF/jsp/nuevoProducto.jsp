<%-- 
    Document   : nuevoProducto
    Created on : 14 may 2022, 17:31:59
    Author     : alvar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aliebay.entity.Producto" %>
<%@page import="aliebay.entity.Vendedor" %>
<%@page import="aliebay.entity.Categoria" %>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo producto</title>
    </head>
    <% 
        Producto producto = (Producto)request.getAttribute("producto");
        String vendedor = (String)request.getAttribute("vendedor");
        
        %>
    <body>
        <h1>Datos producto</h1>
        <form action="NuevoProductoServlet" method="POST">
        <input type="hidden" name="id" value="<%= producto == null ? "" : producto.getIdProducto() %>"/>
        <table>
            <tr>
                <td>Título:</td>
                <td><input type="text" name="titulo" value="<%= producto == null ? "" : producto.getTitulo()%>" /></td>
            </tr>
            <tr>
                <td>Descripción:</td>
                <td><input type="text" name="descripcion" value="<%= producto == null ? "" : producto.getDescripcion() %>" /></td>
            </tr><!-- comment -->
            <tr>
                <td>Precio de salida:</td>
                <td><input type="text" name="precioSalida" value="<%= producto == null ? "" : producto.getPrecioSalida()%>" /></td>
            </tr>
            <tr>
                <td>URL a foto del producto:</td>
                <td><input type="text" name="urlFoto" value="<%= producto == null ? "" : producto.getURLFoto()%>" /></td>
            </tr>
            <tr>
                <td>Fecha de salida: </td>
                <td><input type="text" name="fechaSalida" value="<%= producto == null ? "" : producto.getFechaSalida()%>" /></td>
            </tr><!-- comment -->
            <tr>
                <td>Fecha de fin: </td>
                <td><input type="text" name="fechaFin" value="<%= producto == null ? "" : producto.getFechaFin()%>" /></td>
            </tr>
            <%
                List<Categoria> categorias = (List)request.getAttribute("categorias");
              %>
            <tr>
                <td> <label for="categorias">Categoría:</label>
                        <select name="categorias" id="categorias">
                            <option value="" selected disabled hidden></option>
                        <%
                            for(Categoria c : categorias) {
                        %>
                                <option value="<%= c.getIdCategoria()%>"><%= c.getIdCategoria()%></option>
                        <%
                            }
                        %>
                        </select></td>
            </tr>
            <input type="hidden" name="id" value="<%= vendedor == null ? "" : producto.getIdVendedor() %>"/>
        </form>
        </body>
</html>
