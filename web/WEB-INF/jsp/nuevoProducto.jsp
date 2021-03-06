<%-- 
    Document   : nuevoProducto
    Created on : 14 may 2022, 17:31:59
    Author     : alvar
--%>

<%@page import="aliebay.dto.CategoriaDTO"%>
<%@page import="aliebay.dto.ProductoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo producto</title>
    </head>
    <% 
        ProductoDTO producto = (ProductoDTO)request.getAttribute("idprod");

        String vendedor = (String)request.getAttribute("id");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        
        %>
    <body>
        <jsp:include page="/WEB-INF/jsp/cabeceraVendedor.jsp" />
        <h1>Datos producto</h1>
        <form action="NuevoProductoServlet?id=<%=vendedor%>&idprod=<%=producto == null ? "" : producto.getIdProducto() %>" method="POST">
        <input type="hidden" name="idprod" value="<%= producto == null ? "" : producto.getIdProducto() %>"/>
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
                <td><input type="text" name="urlFoto" value="<%= producto == null ? "" : producto.getuRLFoto()%>" /></td>
            </tr>
            <tr>
                <td>Fecha de salida: </td>
                <td><input type="text" name="fechaSalida" value="<%= producto == null ? "" : sdf.format(producto.getFechaSalida())%>" /></td>
            </tr><!-- comment -->
            <tr>
                <td>Fecha de fin: </td>
                <td><input type="text" name="fechaFin" value="<%= producto == null ? "" : sdf.format(producto.getFechaFin())%>" /></td>
            </tr>
            <%
                List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");
              %>
            <tr>
                <td> <label for="categorias">Categoría:</label>
                        <select name="categorias" id="categorias">
                            <option value="" selected disabled hidden></option>
                        <%
                            for(CategoriaDTO c : categorias) {
                                String selected = "";
                                if(producto != null && c.getIdCategoria().equals(producto.getCategoria().getIdCategoria())){
                                    selected = "selected";
                                }
                        %>
                        <option value="<%= c.getIdCategoria()%>" <%=selected%>><%= c.getIdCategoria()%></option>
                        <%
                            }
                        %>
                        </select></td>
            </tr>
            <input type="hidden" name="id" value="<%= vendedor == null ? "" : vendedor %>"/>
            <td><input type="submit" value="Aplicar cambios" /></td>
        </form>
        </body>
</html>
