<%-- 
    Document   : vendedor
    Created on : 14 may 2022, 18:25:07
    Author     : alvar
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.dto.ProductoDTO"%>
<%@page import="aliebay.dto.UsuarioDTO"%>
<%@page import="aliebay.dto.VendedorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
        <%
            Usuario user = (Usuario) session.getAttribute("usuario");
            Vendedor v = user.getVendedor();
         %>
        <a href="EditarNuevoProductoServlet?id=<%=v.getIdUsuario()%>">Añadir nuevo producto</a><br>
        
        <%
            List<ProductoDTO> productosVendidos = (List)request.getAttribute("productosVendidos");
            List<ProductoDTO> productosNoVendidos = (List)request.getAttribute("productosNoVendidos");
            
            String categoria = (String)request.getAttribute("categoria");
            
            String llamada = "este usuario";
            if(categoria != null){
                llamada = "categoria " + categoria;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            if(productosVendidos.isEmpty() && productosNoVendidos.isEmpty()){
        %>
                <h2> No existen productos para <%=llamada%></h2>
        <%
            }else{
                if(categoria != null){               
        %>
                <h2> Productos para categoria <%=categoria %>: </h2>
        <%
            } if(!productosNoVendidos.isEmpty()){
            %>
            
            <h3>Productos en venta:</h3>
            <table border="1" width="80%" style="text-align:center">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Precio de salida</th>
                <th>Foto del producto</th>
                <th>Fecha de salida</th>
                <th>Fecha de fin:</th>
                <th></th><!-- Editar -->
            </tr>
        <%
                for (ProductoDTO p: productosNoVendidos) {
        %>
        <tr>
            <td><%= p.getTitulo()%></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getCategoria().getIdCategoria() %></td>
            <td><%= p.getPrecioSalida() %></td>
            <td><a href="verFotoServlet?url=<%= p.getURLFoto() %>">Ver Foto</a></td>
            <td><%=  sdf.format(p.getFechaSalida()) %></td>
            <td><%= sdf.format(p.getFechaFin()) %></td>
            <td><a href="EditarNuevoProductoServlet?id=<%=p.getIdProducto()%>&titulo=<%=p.getTitulo()%>&descripcion=<%=p.getDescripcion%>&precioSalida=<%=p.getPrecioSalida()%>&urlFoto=<%=p.getURLFoto()%>&fechaSalida=<%=p.getFechaSalida()%>&fechaFin=<%=p.getFechaFin()%>">Editar</a></td>
        </tr>
        </table>
        <%
                    }
                } 
                if(!productosVendidos.isEmpty()){
        %>
                    
        <h3>Productos vendidos:</h3>
            <table border="1" width="80%" style="text-align:center">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Precio compra</th>
                <th>Comprador</th>
                <th>Fecha compra</th>
            </tr>
        <%
                for (ProductoDTO p: productosNoVendidos) {
        %>
        <tr>
            <td><%= p.getTitulo()%></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getCategoria().getIdCategoria() %></td>
            <td><%= p.getVenta().getPrecioVenta() %></td>
            <td><%= p.getVenta().getComprador().getUsuario().getUserName() %></td>
            <td><%= sdf.format(p.getVenta().getFecha()) %></td>
        </tr>
        </table>
        <%
                }
            }
        }
            %>
        
    </body>
</html>