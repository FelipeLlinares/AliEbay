<%-- 
    Document   : productos
    Created on : 25 abr 2022, 11:57:30
    Author     : felip
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.entity.Producto"%>
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
            List<Producto> productosVendidos = (List)request.getAttribute("productosVendidos");
            List<Producto> productosNoVendidos = (List)request.getAttribute("productosNoVendidos");
            
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
            </tr>
        <%
                for (Producto p: productosNoVendidos) {
        %>
        <tr>
            <td><%= p.getTitulo()%></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getCategoria().getIdCategoria() %></td>
            <td><%= p.getPrecioSalida() %></td>
            <td><a href="verFotoServlet?url=<%= p.getURLFoto() %>">Ver Foto</a></td>
            <td><%=  sdf.format(p.getFechaSalida()) %></td>
            <td><%= sdf.format(p.getFechaFin()) %></td>
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
                for (Producto p: productosNoVendidos) {
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
